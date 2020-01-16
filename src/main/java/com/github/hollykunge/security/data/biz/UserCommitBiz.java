package com.github.hollykunge.security.data.biz;

import com.github.hollykunge.security.common.biz.BaseBiz;
import com.github.hollykunge.security.common.exception.BaseException;
import com.github.hollykunge.security.common.util.EntityUtils;
import com.github.hollykunge.security.common.util.ExceptionCommonUtil;
import com.github.hollykunge.security.data.constants.TaskContants;
import com.github.hollykunge.security.data.dictionary.CommitStatusEnum;
import com.github.hollykunge.security.data.dictionary.ErrorCodeEnum;
import com.github.hollykunge.security.data.dictionary.MergeStatusEnum;
import com.github.hollykunge.security.data.dto.GitCommitDTO;
import com.github.hollykunge.security.data.entity.TaskEntity;
import com.github.hollykunge.security.data.entity.UserCommitEntity;
import com.github.hollykunge.security.data.entity.UserTaskMap;
import com.github.hollykunge.security.data.exception.TaskBizException;
import com.github.hollykunge.security.data.factory.GitFactory;
import com.github.hollykunge.security.data.mapper.UserCommitMapper;
import com.github.hollykunge.security.data.strategy.UserOptionJgitInterface;
import com.github.hollykunge.security.data.util.PermissionUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhhongyu
 * @description:
 * @since: Create in 10:39 2019/11/6
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserCommitBiz extends BaseBiz<UserCommitMapper, UserCommitEntity> {
    @Autowired
    private UserOptionJgitInterface userOptionJgitInterface;
    @Autowired
    private GitFactory gitFactory;
    @Autowired
    private TaskInfoBiz taskInfoBiz;
    @Autowired
    private UserTaskBiz userTaskBiz;

    @Override
    protected String getPageName() {
        return "UserCommitBiz";
    }

    /**
     * 保存方法
     * @param taskId 任务id
     * @param userId 用户id
     * @param userName 用户姓名
     * @return
     * @throws Exception
     */
    public boolean save (String taskId,String userId,String userName) throws Exception {
        getUserTaskMap(taskId,userId,userName,null);
        userOptionJgitInterface.gitAdd();
        gitFactory.gitClose();
        return true;
    }

    /**
     * 提交
     * @param taskId 任务id
     * @param userId 用户id
     * @param userName 用户姓名
     * @param dec 提交描述（必填）
     * @return
     */
    public boolean commit(String taskId,String userId,String userName,String dec) throws Exception{
        if(StringUtils.isEmpty(dec)){
            throw new BaseException("提交描述不能为空");
        }
        Map<String,Object> result = getUserTaskMap(taskId, userId, userName,dec);
        UserTaskMap userTaskMap = (UserTaskMap) result.get("userTaskMap");
        GitCommitDTO currentUser = (GitCommitDTO) result.get("currentUser");
        TaskEntity taskEntity = (TaskEntity) result.get("task");
        userOptionJgitInterface.setGit(gitFactory.getGit(taskEntity.getGitRepoName()));
        RevCommit revCommit = userOptionJgitInterface.gitCommit(currentUser);
        String commitId = revCommit.getName();
        //1.插入数据库表commitInfor中，如果插入数据库失败，将git回退到父级节点的位置
        UserCommitEntity userCommitEntity = getUserCommitEntity(userTaskMap, commitId, dec,
                MergeStatusEnum.MERGING_STATUS.getValue(), CommitStatusEnum.COMMIT_STATUS.getValue());
        try{
            mapper.insertSelective(userCommitEntity);
        }catch(Exception e){
            log.error(ExceptionCommonUtil.getExceptionMessage(e));
            //回退到父节点版本
            userOptionJgitInterface.rollBackPreRevision(commitId);
            throw e;
        }
        //2.merge到task默认分支，将commitinfor中的mergestatus置位merging
        String mergeStatus = MergeStatusEnum.MERGED_STATUS.getValue();
        try{
            userOptionJgitInterface.mergeChanges(getMergeUserDTO(taskEntity, TaskContants.GIT_BRANCH_TO_MERGE_BRANCH));
        }catch (TaskBizException e){
            //合并产生冲突时, 如果和主分支产生冲突时，将mergestatus置位conficting
            if(e.getStatus() == ErrorCodeEnum.TASK_MERGE_ERROR.code()){
                mergeStatus = MergeStatusEnum.CONFLICT_STATUS.getValue();
            }
        }
        //4.如果和主分支没有发生冲突时，将mergestatus置位merged
        userCommitEntity.setMergeStatus(mergeStatus);
        try{
            mapper.updateByPrimaryKeySelective(userCommitEntity);
        }catch (Exception e){
            log.error(ExceptionCommonUtil.getExceptionMessage(e));
            //回退到父节点版本
            userOptionJgitInterface.rollBackPreRevision(commitId);
            throw e;
        }
        gitFactory.gitClose();
        return true;
    }

    /**
     * 执行人发布版本（发布暂时不加了）
     * @param taskId
     * @param userId
     * @param userName
     * @return
     * @throws Exception
     */
    public boolean send(String taskId,String userId,String userName) throws Exception{
        TaskEntity taskEntity = taskInfoBiz.selectById(taskId);
        if(StringUtils.isEmpty(taskId) || taskEntity == null){
            throw new BaseException("没有对应的任务...");
        }
        //校验是否是执行人
        PermissionUtils.initGitPermission(userId,taskEntity.getTaskExecutorId());
        //校验是否含有冲突的分支
        UserCommitEntity userCommitEntity = new UserCommitEntity();
        userCommitEntity.setMergeStatus(MergeStatusEnum.CONFLICT_STATUS.getValue());
        List<UserCommitEntity> conflictCommits = mapper.select(userCommitEntity);
        UserTaskMap userTaskMapTemp = new UserTaskMap();
        userTaskMapTemp.setTaskId(taskId);
        List<UserTaskMap> userTaskMaps = userTaskBiz.selectList(userTaskMapTemp);
        boolean isExitConflict = conflictCommits.stream().anyMatch(entity ->
                userTaskMaps.stream().anyMatch(userMap ->
                        Objects.equals(userMap.getId(),entity.getUserTaskId())));
        if(isExitConflict){
            throw new BaseException("任务中存在冲突，不能进行发布...");
        }
        //没有冲突继续
        userOptionJgitInterface.setGitAndUser(getMergeUserDTO(taskEntity, TaskContants.GIT_BRANCH_FROM_MERGE_BRANCH),
                gitFactory.getGit(taskEntity.getGitRepoName()));
        for (UserTaskMap userTask:
             userTaskMaps) {
            GitCommitDTO gitCommitDTO = gitFactory.generateCurrentGitUser(userTask.getUserName(), userTask.getUserId());
            setCurrentCommitDTO(gitCommitDTO,null,userTask,null,TaskContants.GIT_BRANCH_TO_MERGE_BRANCH);
            userOptionJgitInterface.mergeChanges(gitCommitDTO);
            //当为执行人分支时，保存发布版本数据
            if(Objects.equals(userTask.getUserId(),userId)){
                //todo:存到数据库表commitinfo
            }
        }
        gitFactory.gitClose();
        //记录一下发布版本号
        return true;
    }
    private GitCommitDTO getMergeUserDTO(TaskEntity taskEntity ,String mergeFlag){
        GitCommitDTO gitCommitDTO = new GitCommitDTO();
        gitCommitDTO.setBranch(taskEntity.getDefultBranch());
        gitCommitDTO.setMergeBranchFlag(mergeFlag);
        return gitCommitDTO;
    }

    private void getCommitAndParentId(String commitId,String parentId,Iterable<RevCommit> revCommits){
        AtomicInteger count = new AtomicInteger();
        for(RevCommit revCommit : revCommits){
            int i = count.getAndIncrement();
            if(i == 0){
                commitId = revCommit.getId().getName();
            }
            if(i == 1){
                parentId = revCommit.getId().getName();
            }
            break;
        }
    }
    private UserCommitEntity getUserCommitEntity(UserTaskMap userTaskMap,
                                                 String commitId,
                                                 String dec,
                                                 String mergStatus,
                                                 String commitStatus){
        UserCommitEntity userCommitEntity = new UserCommitEntity();
        userCommitEntity.setBranch(userTaskMap.getGitBranchName());
        userCommitEntity.setGitCommitId(commitId);
        userCommitEntity.setUserTaskId(userTaskMap.getId());
        userCommitEntity.setDec(dec);
        userCommitEntity.setMergeStatus(mergStatus);
        userCommitEntity.setStatus(commitStatus);
        EntityUtils.setCreatAndUpdatInfo(userCommitEntity);
        return userCommitEntity;
    }

    private Map<String,Object> getUserTaskMap(String taskId, String userId, String userName,String dec) throws Exception{
        if(StringUtils.isEmpty(taskId) || StringUtils.isEmpty(userId)){
            throw new BaseException("任务id或用户不能为空...");
        }
        TaskEntity taskEntity = taskInfoBiz.selectById(taskId);
        if(taskEntity == null){
            throw new BaseException("查询不到该任务...");
        }
        if(!isTaskUser(taskId,userId)){
            throw new BaseException("你不在这个任务中,不能保存...");
        }
        UserTaskMap userTaskMap = new UserTaskMap();
        userTaskMap.setTaskId(taskId);
        userTaskMap.setUserId(userId);
        List<UserTaskMap> userTaskMaps = userTaskBiz.selectList(userTaskMap);
        GitCommitDTO currentCommitDTO = gitFactory.generateCurrentGitUser(userName,userId);
        setCurrentCommitDTO(currentCommitDTO,TaskContants.FULL_FILE_PATH,userTaskMaps.get(0),dec,TaskContants.GIT_BRANCH_FROM_MERGE_BRANCH);
        Git git = gitFactory.getGit(taskEntity.getGitRepoName());
        userOptionJgitInterface.setGitAndUser(currentCommitDTO,git);
        Map<String,Object> result = new HashMap<>(256);
        result.put("userTaskMap",userTaskMaps.get(0));
        result.put("currentUser",currentCommitDTO);
        result.put("task",taskEntity);
        return result;
    }
    private void setCurrentCommitDTO(GitCommitDTO currentCommitDTO,String filePath,UserTaskMap userTaskMap,String dec,String mergeFlag){
        currentCommitDTO.setFilePath(filePath);
        currentCommitDTO.setBranch(userTaskMap.getGitBranchName());
        currentCommitDTO.setDecription(dec);
        currentCommitDTO.setMergeBranchFlag(mergeFlag);
    }

    private boolean isTaskUser(String taskId,String userId){
        UserTaskMap userTaskMap = new UserTaskMap();
        userTaskMap.setTaskId(taskId);
        List<UserTaskMap> userTaskMaps = userTaskBiz.selectList(userTaskMap);
        return userTaskMaps.stream().anyMatch((UserTaskMap userTaskEntity) -> Objects.equals(userId,userTaskEntity.getUserId()));
    }
}
