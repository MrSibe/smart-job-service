package com.xibei.smartjobservice.service;

import com.xibei.smartjobservice.model.entity.SysUser;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 获取所有用户
     */
    List<SysUser> getAllUsers();

    /**
     * 根据ID获取用户
     */
    SysUser getUserById(Long userId);

    /**
     * 根据手机号获取用户
     */
    SysUser getUserByPhone(String phone);

    /**
     * 创建用户
     */
    boolean createUser(SysUser user);

    /**
     * 更新用户
     */
    boolean updateUser(SysUser user);

    /**
     * 删除用户
     */
    boolean deleteUser(Long userId);

    /**
     * 启用用户
     */
    boolean enableUser(Long userId);

    /**
     * 禁用用户
     */
    boolean disableUser(Long userId);

    /**
     * 修改密码
     */
    boolean changePassword(Long userId, String newPassword);
}