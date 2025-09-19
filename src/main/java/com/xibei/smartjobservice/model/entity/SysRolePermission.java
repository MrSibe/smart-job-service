package com.xibei.smartjobservice.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色-权限关联实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_permission")
public class SysRolePermission {

    /**
     * 角色ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;
}