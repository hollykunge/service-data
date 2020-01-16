package com.github.hollykunge.security.data.service.impl;

import com.github.hollykunge.security.data.domain.TemplateInfo;
import com.github.hollykunge.security.data.repository.TemplateInfoRepository;
import com.github.hollykunge.security.data.service.TemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhhongyu
 * @description:
 * @since: Create in 19:01 2019/11/11
 */
@Service
public class TemplateInfoServiceImpl implements TemplateInfoService {
    @Autowired
    private TemplateInfoRepository templateInfoRepository;

    @Override
    public TemplateInfo add(TemplateInfo templateInfo){
        TemplateInfo templateInfo1 = templateInfoRepository.save(templateInfo);
        return templateInfo1;
    }
}
