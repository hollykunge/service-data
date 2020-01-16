package com.github.hollykunge.security.data.rest.template;

import com.github.hollykunge.security.common.msg.ObjectRestResponse;
import com.github.hollykunge.security.data.domain.TemplateInfo;
import com.github.hollykunge.security.data.service.TemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zhhongyu
 * @description: 生成模板主信息接口
 * @since: Create in 19:02 2019/11/11
 */
@Controller
@RequestMapping("/template")
public class TemplateInfoController {
    @Autowired
    private TemplateInfoService templateInfoService;

    @PostMapping("add")
    @ResponseBody
    public ObjectRestResponse<TemplateInfo> add(@RequestBody TemplateInfo templateInfo){
        return new ObjectRestResponse<TemplateInfo>().rel(true).data(templateInfoService.add(templateInfo));
    }
}
