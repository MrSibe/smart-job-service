package com.xibei.smartjobservice.service;

import com.xibei.smartjobservice.model.entity.SysRole;

import java.util.List;

/**
 * 角色服务接口
 */
public interface RoleService {

    /**
     * 获取所有角色
     */
    List<SysRole> getAllRoles();

    /**
     * 根据用户ID获取角色列表
     */
    List<SysRole> getRolesByUserId(Long userId);

    /**
     * 根据ID获取角色
     */
    SysRole getRoleById(Long roleId);

    /**
     * 创建角色
     */
    boolean createRole(SysRole role);

    /**
     * 更新角色
     */
    boolean updateRole(SysRole role);

    /**
     * 删除角色
     */
    boolean deleteRole(Long roleId);

    /**
     * 为用户分配角色
     */
    boolean assignRoleToUser(Long userId, Long roleId);

    /**
     * 移除用户的角色
     */
    boolean removeRoleFromUser(Long userId, Long roleId);

    /**
     * 为角色分配权限
     */
    boolean assignPermissionToRole(Long roleId, Long permissionId);

    /**
     * 移除角色的权限
     */
    boolean removePermissionFromRole(Long roleId, Long permissionId);
}