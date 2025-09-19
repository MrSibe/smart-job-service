package com.xibei.smartjobservice.service.impl;

import com.xibei.smartjobservice.model.entity.SysPermission;
import com.xibei.smartjobservice.mapper.SysPermissionMapper;
import com.xibei.smartjobservice.mapper.SysRolePermissionMapper;
import com.xibei.smartjobservice.mapper.SysUserRoleMapper;
import com.xibei.smartjobservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 权限服务实现
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final SysPermissionMapper permissionMapper;
    private final SysRolePermissionMapper rolePermissionMapper;
    private final SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysPermission> getAllPermissions() {
        return permissionMapper.selectList(null);
    }

    @Override
    public List<String> getPermissionsByUserId(Long userId) {
        // 直接查询用户的所有权限
        return permissionMapper.getPermissionsByUserId(userId);
    }

    @Override
    public List<SysPermission> getPermissionsByRoleId(Long roleId) {
        return rolePermissionMapper.selectList(null).stream()
                .filter(rolePermission -> rolePermission.getRoleId().equals(roleId))
                .map(rolePermission -> permissionMapper.selectById(rolePermission.getPermissionId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}