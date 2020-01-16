package com.github.hollykunge.security.data.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: zhhongyu
 * @description: 模板与行映射表
 * @since: Create in 16:22 2019/11/11
 */
@Data
@Document
public class ExcelRowCellMap {
    @Id
    private String id;
    @Indexed
    private String templateId;
    /**
     * 行数(look:要设置唯一)
     */
    private int rowNum;
    /**
     * 是否是excel表头行
     */
    private Boolean isHead;
}
