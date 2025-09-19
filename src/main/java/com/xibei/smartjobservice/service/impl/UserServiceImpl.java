package com.xibei.smartjobservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xibei.smartjobservice.constant.Constants;
import com.xibei.smartjobservice.model.entity.SysUser;
import com.xibei.smartjobservice.mapper.SysUserMapper;
import com.xibei.smartjobservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务实现
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SysUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<SysUser> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public SysUser getUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public SysUser getUserByPhone(String phone) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        return userMapper.selectOne(wrapper);
    }

    @Override
    @Transactional
    public boolean createUser(SysUser user) {
        // 检查手机号是否已存在
        SysUser existingUser = getUserByPhone(user.getPhone());
        if (existingUser != null) {
            throw new RuntimeException("手机号已存在");
        }

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsEnabled(Constants.USER_ENABLED);

        return userMapper.insert(user) > 0;
    }

    @Override
    @Transactional
    public boolean updateUser(SysUser user) {
        // 更新时不能修改密码，密码修改使用单独的方法
        user.setPassword(null);
        return userMapper.updateById(user) > 0;
    }

    @Override
    @Transactional
    public boolean deleteUser(Long userId) {
        return userMapper.deleteById(userId) > 0;
    }

    @Override
    @Transactional
    public boolean enableUser(Long userId) {
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            return false;
        }
        user.setIsEnabled(Constants.USER_ENABLED);
        return userMapper.updateById(user) > 0;
    }

    @Override
    @Transactional
    public boolean disableUser(Long userId) {
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            return false;
        }
        user.setIsEnabled(Constants.USER_DISABLED);
        return userMapper.updateById(user) > 0;
    }

    @Override
    @Transactional
    public boolean changePassword(Long userId, String newPassword) {
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        return userMapper.updateById(user) > 0;
    }
}