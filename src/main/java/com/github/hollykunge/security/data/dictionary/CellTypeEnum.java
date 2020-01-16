package com.github.hollykunge.security.data.dictionary;

import java.util.Objects;

/**
 * @author: zhhongyu
 * @description: 单元格类型扩展字段
 * @since: Create in 16:36 2019/11/11
 */
public enum CellTypeEnum {
    INPUT("文本","11"),
    SELECT("下拉","12"),
    CLICK("点击","13"),
    RADIO("单选","14"),
    CHECKBOX("复选","15");
    private String name;
    private String value;
    CellTypeEnum(String name,String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static CellTypeEnum getEnum(String value) {
        for (CellTypeEnum ele : CellTypeEnum.values()) {
            if (Objects.equals(ele.getValue(),value)) {
                return ele;
            }
        }
        return null;
    }
}
