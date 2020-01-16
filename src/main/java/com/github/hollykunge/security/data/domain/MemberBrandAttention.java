package com.github.hollykunge.security.data.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 模板域
 */
@Data
@Document
public class MemberBrandAttention {
    @Id
    private String id;
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long brandId;
    private String brandName;
    private String brandLogo;
    private String brandCity;
    private Integer brandAttentionCount;
    private Date createTime;
}
