package com.github.hollykunge.security.data.repository;

import com.github.hollykunge.security.data.domain.ExcelRowCellMap;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: zhhongyu
 * @description: 行和excel映射接口
 * @since: Create in 19:22 2019/11/11
 */
public interface ExcelRowCellMapRepository extends MongoRepository<ExcelRowCellMap,String> {
}
