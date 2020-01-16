package com.github.hollykunge.security.data.dictionary;

import java.util.Objects;

/**
 * @author: zhhongyu
 * @description:模板类型扩展类
 * @since: Create in 15:50 2019/11/11
 */
public enum TemplateTypeEnum {
    EXCEL("表格","excel"),
    ORTHER("未知","orther");



    private String name;
    private String value;
    TemplateTypeEnum(String name,String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static TemplateTypeEnum getEnum(String value) {
        for (TemplateTypeEnum ele : TemplateTypeEnum.values()) {
            if (Objects.equals(ele.getValue(),value)) {
                return ele;
            }
        }
        return null;
    }
}
