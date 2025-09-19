package com.xibei.smartjobservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xibei.smartjobservice.model.entity.SysRole;
import com.xibei.smartjobservice.model.entity.SysUserRole;
import com.xibei.smartjobservice.mapper.SysRoleMapper;
import com.xibei.smartjobservice.mapper.SysUserRoleMapper;
import com.xibei.smartjobservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色服务实现
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final SysRoleMapper roleMapper;
    private final SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysRole> getAllRoles() {
        return roleMapper.selectList(null);
    }

    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);

        return userRoleMapper.selectList(wrapper).stream()
                .map(userRole -> roleMapper.selectById(userRole.getRoleId()))
                .filter(role -> role != null)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public SysRole getRoleById(Long roleId) {
        return roleMapper.selectById(roleId);
    }

    @Override
    @Transactional
    public boolean createRole(SysRole role) {
        return roleMapper.insert(role) > 0;
    }

    @Override
    @Transactional
    public boolean updateRole(SysRole role) {
        return roleMapper.updateById(role) > 0;
    }

    @Override
    @Transactional
    public boolean deleteRole(Long roleId) {
        // 删除前需要检查是否有用户关联
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        long count = userRoleMapper.selectCount(wrapper);

        if (count > 0) {
            throw new RuntimeException("无法删除角色，仍有用户关联此角色");
        }

        return roleMapper.deleteById(roleId) > 0;
    }

    @Override
    @Transactional
    public boolean assignRoleToUser(Long userId, Long roleId) {
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

        return userRoleMapper.insert(userRole) > 0;
    }

    @Override
    @Transactional
    public boolean removeRoleFromUser(Long userId, Long roleId) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("role_id", roleId);

        return userRoleMapper.delete(wrapper) > 0;
    }

    @Override
    public boolean assignPermissionToRole(Long roleId, Long permissionId) {
        // 需要在SysRolePermissionMapper中实现
        return false;
    }

    @Override
    public boolean removePermissionFromRole(Long roleId, Long permissionId) {
        // 需要在SysRolePermissionMapper中实现
        return false;
    }
}