package com.github.hollykunge.security.data.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author: zhhongyu
 * @description:模板主信息
 * @since: Create in 15:48 2019/11/11
 */
@Data
@Document
public class TemplateInfo {
    @Id
    private String id;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板类型（通过TemplateTypeEnum进行扩充）
     */
    private String type;
    /**
     * 生成excel的行数
     */
    private int rowCount;
    /**
     * 生成excel的列数
     */
    private int columnCount;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 描述
     */
    private String dec;
}
