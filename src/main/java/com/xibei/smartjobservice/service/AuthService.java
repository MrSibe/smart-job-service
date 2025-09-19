package com.xibei.smartjobservice.service;

import com.xibei.smartjobservice.model.dto.LoginResponse;
import com.xibei.smartjobservice.model.dto.Result;

/**
 * 认证服务接口
 */
public interface AuthService {

    /**
     * 用户登录
     */
    Result<LoginResponse> login(String phone, String password);

    /**
     * 用户注册
     */
    Result<String> register(String phone, String password);

    /**
     * 用户登出
     */
    Result<String> logout();

    /**
     * 刷新令牌
     */
    Result<String> refreshToken(String refreshToken);
}