package com.github.hollykunge.security.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhhongyu
 * @deprecation 用户任务映射实体类
 */
@Table(name = "USER_TASK_MAP")
@Data
@Entity
public class UserTaskMap extends BaseEntity {

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "TASK_ID")
    private String taskId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "PERMISSION")
    private String permission;

    @Column(name = "GIT_BRANCH_NAME")
    private String gitBranchName;

    @Column(name = "STATUS")
    private String status;

}
