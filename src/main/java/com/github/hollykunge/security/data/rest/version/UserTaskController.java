package com.github.hollykunge.security.data.rest.version;

import com.github.hollykunge.security.common.msg.ObjectRestResponse;
import com.github.hollykunge.security.common.rest.BaseController;
import com.github.hollykunge.security.data.biz.UserTaskBiz;
import com.github.hollykunge.security.data.entity.UserTaskMap;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author: zhhongyu
 * @description: 用户任务接口
 * @since: Create in 11:11 2019/9/5
 */
@RestController
@RequestMapping("userTask")
public class UserTaskController extends BaseController<UserTaskBiz, UserTaskMap> {
    /**
     * 拉取人员参与协同编辑
     * @param entity
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<UserTaskMap> pullTaskUser(@RequestBody UserTaskMap entity) throws Exception {
        baseBiz.pullTaskUser(entity,request.getHeader("userId"));
        return new ObjectRestResponse<UserTaskMap>().rel(true);
    }

    /**
     * 删除协同人员
     * @param id 协同人员map主键
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "remove", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<UserTaskMap> removeTaskUser(String id) throws Exception {
        baseBiz.removeTaskUser(request.getHeader("userId"),id);
        return new ObjectRestResponse().rel(true);
    }
}
