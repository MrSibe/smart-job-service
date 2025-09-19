package com.xibei.smartjobservice.constant;

/**
 * 常量类
 */
public class Constants {

    // 响应状态码
    public static final String CODE_SUCCESS = "200";
    public static final String CODE_ERROR = "500";
    public static final String CODE_UNAUTHORIZED = "401";
    public static final String CODE_FORBIDDEN = "403";
    public static final String CODE_NOT_FOUND = "404";
    public static final String CODE_BAD_REQUEST = "400";

    // 用户状态
    public static final int USER_ENABLED = 1;
    public static final int USER_DISABLED = 0;

    // 岗位状态
    public static final boolean JOB_ACTIVE = true;
    public static final boolean JOB_INACTIVE = false;

    // 岗位类型
    public static final String JOB_TYPE_FULL_TIME = "FULL_TIME";
    public static final String JOB_TYPE_PART_TIME = "PART_TIME";
    public static final String JOB_TYPE_INTERNSHIP = "INTERNSHIP";
    public static final String JOB_TYPE_REMOTE = "REMOTE";

    // 学历要求
    public static final String EDUCATION_NONE = "NONE";
    public static final String EDUCATION_ASSOCIATE = "ASSOCIATE";
    public static final String EDUCATION_BACHELOR = "BACHELOR";
    public static final String EDUCATION_MASTER = "MASTER";
    public static final String EDUCATION_DOCTOR = "DOCTOR";

    // 公司规模
    public static final String COMPANY_SIZE_SMALL = "SMALL";
    public static final String COMPANY_SIZE_MEDIUM = "MEDIUM";
    public static final String COMPANY_SIZE_LARGE = "LARGE";
    public static final String COMPANY_SIZE_ENTERPRISE = "ENTERPRISE";

    // 权限前缀
    public static final String PERMISSION_USER = "user:";
    public static final String PERMISSION_JOB = "job:";
    public static final String PERMISSION_ROLE = "role:";

    // 正则表达式
    public static final String PHONE_REGEX = "^1[3-9]\\d{9}$";
}