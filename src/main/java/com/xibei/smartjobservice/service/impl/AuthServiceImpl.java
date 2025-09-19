package com.xibei.smartjobservice.service.impl;

import com.xibei.smartjobservice.constant.Constants;
import com.xibei.smartjobservice.util.JwtUtils;
import com.xibei.smartjobservice.model.dto.Result;
import com.xibei.smartjobservice.model.dto.LoginResponse;
import com.xibei.smartjobservice.model.entity.SysUser;
import com.xibei.smartjobservice.mapper.SysUserMapper;
import com.xibei.smartjobservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

/**
 * 认证服务实现
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final SysUserMapper userMapper;

    @Override
    public Result<LoginResponse> login(String phone, String password) {
        // 验证手机号格式
        if (!Pattern.matches(Constants.PHONE_REGEX, phone)) {
            return Result.error(Constants.CODE_BAD_REQUEST, "手机号格式不正确");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phone, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            SysUser user = userMapper.selectByPhone(phone);
            String token = jwtUtils.generateToken(user.getId(), phone);

            return Result.success(new LoginResponse(token));
        } catch (Exception e) {
            return Result.error(Constants.CODE_UNAUTHORIZED, "手机号或密码错误");
        }
    }

    @Override
    @Transactional
    public Result<String> register(String phone, String password) {
        // 验证手机号格式
        if (!Pattern.matches(Constants.PHONE_REGEX, phone)) {
            return Result.error(Constants.CODE_BAD_REQUEST, "手机号格式不正确");
        }

        // 检查手机号是否已存在
        SysUser existingUser = userMapper.selectByPhone(phone);
        if (existingUser != null) {
            return Result.error(Constants.CODE_BAD_REQUEST, "手机号已存在");
        }

        // 创建新用户
        SysUser newUser = new SysUser();
        newUser.setPhone(phone);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setIsEnabled(Constants.USER_ENABLED);

        userMapper.insert(newUser);

        return Result.success("注册成功");
    }

    @Override
    public Result<String> logout() {
        SecurityContextHolder.clearContext();
        return Result.success("登出成功");
    }

    @Override
    public Result<String> refreshToken(String refreshToken) {
        // 简单的刷新令牌实现，实际项目中可能需要更复杂的逻辑
        if (jwtUtils.validateToken(refreshToken)) {
            String phone = jwtUtils.getPhoneFromToken(refreshToken);
            Long userId = jwtUtils.getUserIdFromToken(refreshToken);
            String newToken = jwtUtils.generateToken(userId, phone);
            return Result.success(newToken);
        }
        return Result.error(Constants.CODE_UNAUTHORIZED, "刷新令牌无效");
    }
}