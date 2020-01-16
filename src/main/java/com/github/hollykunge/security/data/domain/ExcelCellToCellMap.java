package com.github.hollykunge.security.data.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author: zhhongyu
 * @description: 单元格之间的映射关系
 * @since: Create in 17:00 2019/11/11
 */
@Data
@Document
public class ExcelCellToCellMap {
    @Id
    private String id;
    @Indexed
    private String oneCellId;
    @Indexed
    private String otherCellId;

    private String oneValue;

    private String otherValue;

}
