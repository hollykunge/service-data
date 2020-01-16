package com.github.hollykunge.security.data.rest.version;

import com.github.hollykunge.security.common.msg.ObjectRestResponse;
import com.github.hollykunge.security.common.rest.BaseController;
import com.github.hollykunge.security.data.biz.UserCommitBiz;
import com.github.hollykunge.security.data.entity.UserCommitEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhhongyu
 * @description: 用户保存，提交,发布，合并业务接口
 * @since: Create in 10:11 2019/11/6
 */
@RestController
@RequestMapping("userCommit")
public class UserCommitController extends BaseController<UserCommitBiz, UserCommitEntity> {
    /**
     * 保存方法，参数为任务id：taskId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add/{taskId}", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse save(@PathVariable String taskId) throws Exception {
        //todo:1.首先将表格解析器解析的文件保存到磁盘中
        //2.执行save方法
        baseBiz.save(taskId,request.getHeader("userId"),request.getHeader("userName"));
        return new ObjectRestResponse().rel(true);
    }

    /**
     * 提交方法，
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/commit/{taskId}", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse commit(@PathVariable String taskId, @RequestParam String dec)throws Exception{
        //todo:1.首先将表格解析器解析的文件保存到磁盘中
        //2.commit
        boolean commit = baseBiz.commit(taskId, request.getHeader("userId"), request.getHeader("userName"), dec);
        return new ObjectRestResponse().rel(commit);
    }

    /**
     * 版本发布，只有执行人才会发布版本（暂时不处理发布请求）
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/send/{taskId}", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse send(@PathVariable String taskId)throws Exception{
        boolean send = baseBiz.send(taskId, request.getHeader("userId"), request.getHeader("userName"));
        return new ObjectRestResponse().rel(send);
    }
}
