package com.xibei.smartjobservice.controller;

import com.xibei.smartjobservice.model.dto.Result;
import com.xibei.smartjobservice.model.entity.SysPermission;
import com.xibei.smartjobservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限控制器
 */
@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    /**
     * 获取所有权限列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('role:list')")
    public Result<List<SysPermission>> getAllPermissions() {
        List<SysPermission> permissions = permissionService.getAllPermissions();
        return Result.success(permissions);
    }

    /**
     * 根据用户ID获取权限列表
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('user:list')")
    public Result<List<String>> getPermissionsByUserId(@PathVariable Long userId) {
        List<String> permissions = permissionService.getPermissionsByUserId(userId);
        return Result.success(permissions);
    }

    /**
     * 根据角色ID获取权限列表
     */
    @GetMapping("/role/{roleId}")
    @PreAuthorize("hasAuthority('role:list')")
    public Result<List<SysPermission>> getPermissionsByRoleId(@PathVariable Long roleId) {
        List<SysPermission> permissions = permissionService.getPermissionsByRoleId(roleId);
        return Result.success(permissions);
    }
}