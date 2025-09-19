package com.xibei.smartjobservice.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 岗位实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("job")
public class Job {

    /**
     * 岗位ID（雪花ID）
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位描述
     */
    private String description;

    /**
     * 薪资范围
     */
    private String salary;

    /**
     * 工作地点
     */
    private String location;

    /**
     * 岗位类型（自由输入，如：全职、兼职、实习、远程等）
     */
    private String jobType;

    /**
     * 学历要求（自由输入，如：不限、大专、本科、硕士、博士等）
     */
    private String educationRequired;

    /**
     * 岗位标签
     */
    private String tags;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司规模：SMALL-小型，MEDIUM-中型，LARGE-大型，ENTERPRISE-企业级
     */
    private String companySize;

    /**
     * 是否有效：true有效，false无效
     */
    private Boolean isActive;

    /**
     * 岗位链接
     */
    private String url;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    /**
     * 岗位到期时间
     */
    private LocalDateTime expirationDate;
}