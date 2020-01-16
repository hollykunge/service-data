package com.github.hollykunge.security.data.repository;

import com.github.hollykunge.security.data.domain.ExcelCellToCellMap;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: zhhongyu
 * @description: 单元格与单元格映射接口
 * @since: Create in 19:22 2019/11/11
 */
public interface ExcelCellToCellMapRepository extends MongoRepository<ExcelCellToCellMap,String> {
}
