package com.github.hollykunge.security.data.dictionary;

/**
 * @author: zhhongyu
 * @description: mege状态
 * @since: Create in 10:23 2019/11/6
 */
public enum MergeStatusEnum {
    MERGED_STATUS("合并完成","merged"),
    MERGING_STATUS("合并中","merging"),
    CONFLICT_STATUS("冲突中","conflicting");
    private String name;
    private String value;
    MergeStatusEnum(String name,String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
