package com.github.hollykunge.security.data.factory;


import com.github.hollykunge.security.data.strategy.BaseJgitManager;
import com.github.hollykunge.security.data.util.SpringUtils;

public class JgitFactory {

    public static BaseJgitManager getJgit(String templateId) {
        BaseJgitManager payCallbackTemplate = (BaseJgitManager) SpringUtils.getBean(templateId);
        return payCallbackTemplate;
    }
}
