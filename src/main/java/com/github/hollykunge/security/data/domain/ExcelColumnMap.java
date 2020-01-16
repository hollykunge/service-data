package com.github.hollykunge.security.data.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: zhhongyu
 * @description:模板与列单元格映射表
 * @since: Create in 16:22 2019/11/11
 */
@Data
@Document
public class ExcelColumnMap {
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
     * 单元格类型（与CellTypeEnum映射）
     */
    private String type;
    /**
     * 列数（look: 要设置唯一!!!）
     */
    private int columnNum;
}
