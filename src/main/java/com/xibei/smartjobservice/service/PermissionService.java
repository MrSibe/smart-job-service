package com.xibei.smartjobservice.service;

import com.xibei.smartjobservice.model.entity.SysPermission;

import java.util.List;

/**
 * 权限服务接口
 */
public interface PermissionService {

    /**
     * 获取所有权限
     */
    List<SysPermission> getAllPermissions();

    /**
     * 根据用户ID获取权限列表
     */
    List<String> getPermissionsByUserId(Long userId);

    /**
     * 根据角色ID获取权限列表
     */
    List<SysPermission> getPermissionsByRoleId(Long roleId);
}