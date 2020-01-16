package com.github.hollykunge.security.data.service.impl;

import com.github.hollykunge.security.data.domain.MemberBrandAttention;
import com.github.hollykunge.security.data.repository.MemberBrandAttentionRepository;
import com.github.hollykunge.security.data.service.MemberAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模板方法实现
 * Created by macro on 2018/8/2.
 */
@Service
public class MemberAttentionServiceImpl implements MemberAttentionService {
    @Autowired
    private MemberBrandAttentionRepository memberBrandAttentionRepository;

    @Override
    public int add(MemberBrandAttention memberBrandAttention) {
        int count = 0;
        MemberBrandAttention findAttention = memberBrandAttentionRepository.findByMemberIdAndBrandId(memberBrandAttention.getMemberId(), memberBrandAttention.getBrandId());
        if (findAttention == null) {
            memberBrandAttentionRepository.save(memberBrandAttention);
            count = 1;
        }
        return count;
    }

    @Override
    public int delete(Long memberId, Long brandId) {
        return memberBrandAttentionRepository.deleteByMemberIdAndBrandId(memberId,brandId);
    }

    @Override
    public List<MemberBrandAttention> list(Long memberId) {
        return memberBrandAttentionRepository.findByMemberId(memberId);
    }
}
