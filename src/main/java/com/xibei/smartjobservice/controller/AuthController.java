package com.xibei.smartjobservice.controller;

import com.xibei.smartjobservice.model.dto.LoginRequest;
import com.xibei.smartjobservice.model.dto.LoginResponse;
import com.xibei.smartjobservice.model.dto.RegisterRequest;
import com.xibei.smartjobservice.model.dto.Result;
import com.xibei.smartjobservice.service.AuthService;
import com.xibei.smartjobservice.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;
    private final CaptchaService captchaService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<LoginResponse> login(@Validated @RequestBody LoginRequest request) {
        // 验证验证码
        if (!captchaService.validateCaptcha(request.getCaptchaKey(), request.getCaptchaCode())) {
            return Result.error("验证码错误或已过期");
        }
        return authService.login(request.getPhone(), request.getPassword());
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<String> register(@Validated @RequestBody RegisterRequest request) {
        // 验证验证码
        if (!captchaService.validateCaptcha(request.getCaptchaKey(), request.getCaptchaCode())) {
            return Result.error("验证码错误或已过期");
        }
        return authService.register(request.getPhone(), request.getPassword());
    }

    /**
     * 用户登出
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return authService.logout();
    }

    /**
     * 刷新令牌
     */
    @PostMapping("/refresh")
    public Result<String> refreshToken(@RequestParam String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}