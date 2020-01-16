package com.github.hollykunge.security.data.service;


import com.github.hollykunge.security.data.domain.MemberBrandAttention;

import java.util.List;

/**
 * 模板方法
 */
public interface MemberAttentionService {
    /**
     * 添加关注
     */
    int add(MemberBrandAttention memberBrandAttention);

    /**
     * 取消关注
     */
    int delete(Long memberId, Long brandId);

    /**
     * 获取用户关注列表
     */
    List<MemberBrandAttention> list(Long memberId);
}
