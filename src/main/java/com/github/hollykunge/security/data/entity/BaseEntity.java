package com.github.hollykunge.security.data.entity;

import com.github.hollykunge.security.data.util.UUIdGenId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @description: 实体基类
 * @author: dd
 * @since: 2019-05-16
 */
@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name = "Id",length = 32)
    @ApiModelProperty(value = "主键",hidden = true)
    private String id;

    @Column(name = "CRT_TIME")
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date crtTime;

    @Column(name = "CRT_USER")
    @ApiModelProperty(value = "创建人id",hidden = true)
    private String crtUser;

    @Column(name = "CRT_NAME")
    @ApiModelProperty(value = "创建人姓名",hidden = true)
    private String crtName;

    @Column(name = "CRT_HOST")
    @ApiModelProperty(value = "创建时服务器地址",hidden = true)
    private String crtHost;

    @Column(name = "UPD_TIME")
    @ApiModelProperty(value = "更新时间",hidden = true)
    private Date updTime;

    @Column(name = "UPD_USER")
    @ApiModelProperty(value = "更新人id",hidden = true)
    private String updUser;

    @Column(name = "UPD_NAME")
    @ApiModelProperty(value = "更新人姓名",hidden = true)
    private String updName;

    @Column(name = "UPD_HOST")
    @ApiModelProperty(value = "更新时服务器地址",hidden = true)
    private String updHost;

    @Column(name = "ATTR1")
    @ApiModelProperty(value = "扩展1",hidden = true)
    private String attr1;

    @Column(name = "ATTR2")
    @ApiModelProperty(value = "扩展2",hidden = true)
    private String attr2;

    @Column(name = "ATTR3")
    @ApiModelProperty(value = "扩展3",hidden = true)
    private String attr3;

    @Column(name = "ATTR4")
    @ApiModelProperty(value = "扩展4",hidden = true)
    private String attr4;
}
