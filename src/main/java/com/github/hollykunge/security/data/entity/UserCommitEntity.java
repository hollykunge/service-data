package com.github.hollykunge.security.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: zhhongyu
 * @description: 用户提交信息主表
 * @since: Create in 10:17 2019/11/6
 */
@Data
@Entity
@Table(name = "USER_COMMIT_INFO")
public class UserCommitEntity extends BaseEntity {

    @Column(name = "BRANCH")
    private String branch;
    /**
     * usertaskmap外键
     */
    @Column(name = "USER_TASK_ID")
    private String userTaskId;
    /**
     * git提交的id
     */
    @Column(name = "GIT_COMMIT_ID")
    private String gitCommitId;
    /**
     * 描述
     */
    @Column(name = "DEC")
    private String dec;
    /**
     * 状态（1提交，2发布：只有执行人才可以发布）
     */
    @Column(name = "STATUS")
    private String status;
    /**
     * 提交时合并状态（merged合并完成，merging合并中，conflicting冲突中）
     */
    @Column(name = "MERGE_STATUS")
    private String mergeStatus;

}
