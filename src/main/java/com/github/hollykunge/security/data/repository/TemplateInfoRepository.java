package com.github.hollykunge.security.data.repository;

import com.github.hollykunge.security.data.domain.TemplateInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: zhhongyu
 * @description: 生成模板接口
 * @since: Create in 18:59 2019/11/11
 */
public interface TemplateInfoRepository extends MongoRepository<TemplateInfo,String> {
}
