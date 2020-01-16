package com.github.hollykunge.security.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: zhhongyu
 * @description: 任务实体类
 * @since: Create in 10:52 2019/9/5
 */
@Data
@Table(name = "LARK_TASK_INFO")
@Entity
@ApiModel(value = "任务实体类",description = "包含整个任务的基本属性和附属信息")
public class TaskEntity extends BaseEntity {

    /**
     * 任务描述
     */
    @Column(name = "TASK_DES")
    @ApiModelProperty(value = "任务描述")
    private String taskDes;

    /**
     * 任务执行人
     */
    @Column(name = "TASK_EXECUTOR_ID")
    @ApiModelProperty(value = "任务执行人",required = true,example = "sdlfkju234")
    private String taskExecutorId;
    /**
     * 任务名称
     */
    @Column(name = "NAME")
    @ApiModelProperty(value = "任务名称",required = true,example = "测试任务")
    private String name;
    /**
     * 存放的仓库名称
     */
    @Column(name = "GIT_REPO_NAME")
    @ApiModelProperty(value = "存放的仓库名称",hidden = true)
    private String gitRepoName;
    /**
     * 默认合并到的分支
     */
    @Column(name = "DEFULT_BRANCH")
    @ApiModelProperty(value = "默认合并到的分支",hidden = true)
    private String defultBranch;

    /**
     * 爹任务ID
     */
    @Column(name = "TASK_PARENT_ID")
    @ApiModelProperty(value = "爹任务ID")
    private String taskParentId;

    /**
     * 计划结束时间
     */
    @Column(name = "TASK_PLAN_END")
    @ApiModelProperty(value = "计划结束时间")
    private Date taskPlanEnd;

    /**
     * 任务进度
     */
    @Column(name = "TASK_PROCESS")
    @ApiModelProperty(value = "任务进度")
    private String taskProcess;


    /**
     * 任务资源ID
     */
    @Column(name = "TASK_RESOURCE_ID")
    @ApiModelProperty(value = "任务资源ID",required = true)
    private String taskResourceId;

    /**
     * 任务状态
     */
    @Column(name = "TASK_STATE")
    @ApiModelProperty(value = "任务状态",required = true)
    private String taskState;

    /**
     * 任务实际结束时间
     */
    @Column(name = "TASK_TIME_END")
    @ApiModelProperty(value = "任务实际结束时间")
    private Date taskTimeEnd;

    /**
     * 任务实际开始时间
     */
    @Column(name = "TASK_TIME_START")
    @ApiModelProperty(value = "任务实际开始时间")
    private Date taskTimeStart;

    /**
     * 任务类型
     */
    @Column(name = "TASK_TYPE")
    @ApiModelProperty(value = "任务类型",required = true)
    private String taskType;

}
