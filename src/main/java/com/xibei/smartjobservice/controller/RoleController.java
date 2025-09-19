package com.xibei.smartjobservice.controller;

import com.xibei.smartjobservice.model.dto.Result;
import com.xibei.smartjobservice.model.entity.SysRole;
import com.xibei.smartjobservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 */
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /**
     * 获取所有角色列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('role:list')")
    public Result<List<SysRole>> getAllRoles() {
        List<SysRole> roles = roleService.getAllRoles();
        return Result.success(roles);
    }

    /**
     * 根据ID获取角色
     */
    @GetMapping("/{roleId}")
    @PreAuthorize("hasAuthority('role:list')")
    public Result<SysRole> getRoleById(@PathVariable Long roleId) {
        SysRole role = roleService.getRoleById(roleId);
        return Result.success(role);
    }

    /**
     * 创建角色
     */
    @PostMapping
    @PreAuthorize("hasAuthority('role:create')")
    public Result<String> createRole(@RequestBody SysRole role) {
        boolean success = roleService.createRole(role);
        return success ? Result.success("角色创建成功") : Result.error("角色创建失败");
    }

    /**
     * 更新角色
     */
    @PutMapping("/{roleId}")
    @PreAuthorize("hasAuthority('role:update')")
    public Result<String> updateRole(@PathVariable Long roleId, @RequestBody SysRole role) {
        role.setId(roleId);
        boolean success = roleService.updateRole(role);
        return success ? Result.success("角色更新成功") : Result.error("角色更新失败");
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('role:delete')")
    public Result<String> deleteRole(@PathVariable Long roleId) {
        boolean success = roleService.deleteRole(roleId);
        return success ? Result.success("角色删除成功") : Result.error("角色删除失败");
    }

    /**
     * 为用户分配角色
     */
    @PostMapping("/{roleId}/users/{userId}")
    @PreAuthorize("hasAuthority('user:update')")
    public Result<String> assignRoleToUser(@PathVariable Long roleId, @PathVariable Long userId) {
        boolean success = roleService.assignRoleToUser(userId, roleId);
        return success ? Result.success("角色分配成功") : Result.error("角色分配失败");
    }

    /**
     * 移除用户的角色
     */
    @DeleteMapping("/{roleId}/users/{userId}")
    @PreAuthorize("hasAuthority('user:update')")
    public Result<String> removeRoleFromUser(@PathVariable Long roleId, @PathVariable Long userId) {
        boolean success = roleService.removeRoleFromUser(userId, roleId);
        return success ? Result.success("角色移除成功") : Result.error("角色移除失败");
    }
}