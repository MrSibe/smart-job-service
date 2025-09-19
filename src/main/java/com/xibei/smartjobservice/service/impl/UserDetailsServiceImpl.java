package com.xibei.smartjobservice.service.impl;

import com.xibei.smartjobservice.model.entity.SysUser;
import com.xibei.smartjobservice.mapper.SysUserMapper;
import com.xibei.smartjobservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义UserDetailsService
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserMapper userMapper;
    private final PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        SysUser user = userMapper.selectByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在: " + phone);
        }

        if (!user.getIsEnabled().equals(1)) {
            throw new UsernameNotFoundException("用户已被禁用: " + phone);
        }

        // 获取用户的实际权限
        List<SimpleGrantedAuthority> authorities = permissionService.getPermissionsByUserId(user.getId()).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return User.builder()
                .username(user.getPhone())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}