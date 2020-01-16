package com.github.hollykunge.security.data.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: zhhongyu
 * @description: 单元格值（与列映射关系为一对多）
 * @since: Create in 16:43 2019/11/11
 */
@Data
@Document
public class ExcelCellMap {
    @Id
    private String id;
    @Indexed
    private String templateId;
    /**
     * 与行映射
     */
    @Indexed
    private String rowCellId;
    /**
     * 与列映射
     */
    @Indexed
    private String columnId;
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private String value;
}
