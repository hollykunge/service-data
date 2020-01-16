package com.github.hollykunge.security.data.rest.template;

import com.github.hollykunge.security.common.msg.ListRestResponse;
import com.github.hollykunge.security.common.msg.ObjectRestResponse;
import com.github.hollykunge.security.data.domain.MemberBrandAttention;
import com.github.hollykunge.security.data.service.MemberAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模板接口
 */
@Controller
@RequestMapping("/member/attention")
public class MemberAttentionController {
    @Autowired
    private MemberAttentionService memberAttentionService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse add(@RequestBody MemberBrandAttention memberBrandAttention) {
        int count = memberAttentionService.add(memberBrandAttention);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse delete(Long memberId, Long brandId) {
        int count = memberAttentionService.delete(memberId,brandId);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/list/{memberId}", method = RequestMethod.GET)
    @ResponseBody
    public ListRestResponse<List<MemberBrandAttention>> list(@PathVariable Long memberId) {
        List<MemberBrandAttention> memberBrandAttentionList = memberAttentionService.list(memberId);
        return new ListRestResponse<>("",memberBrandAttentionList.size(),memberBrandAttentionList);
    }
}
