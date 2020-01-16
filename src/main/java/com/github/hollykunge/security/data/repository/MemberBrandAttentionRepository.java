package com.github.hollykunge.security.data.repository;

import com.github.hollykunge.security.data.domain.MemberBrandAttention;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 模板repository
 */
public interface MemberBrandAttentionRepository extends MongoRepository<MemberBrandAttention,String> {
    MemberBrandAttention findByMemberIdAndBrandId(Long memberId, Long brandId);
    int deleteByMemberIdAndBrandId(Long memberId, Long brandId);
    List<MemberBrandAttention> findByMemberId(Long memberId);
}
