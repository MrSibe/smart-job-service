package com.xibei.smartjobservice.controller;

import com.xibei.smartjobservice.model.dto.CaptchaResponse;
import com.xibei.smartjobservice.model.dto.Result;
import com.xibei.smartjobservice.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码控制器
 */
@Tag(name = "验证码", description = "验证码相关接口")
@RestController
@RequestMapping("/api/v1/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @Operation(summary = "获取验证码", description = "获取图形验证码图片和对应的key")
    @GetMapping
    public Result<CaptchaResponse> getCaptcha() {
        try {
            String captchaKey = captchaService.generateCaptchaKey();
            String captchaImage = captchaService.generateCaptchaImage(captchaKey);

            CaptchaResponse response = new CaptchaResponse();
            response.setCaptchaKey(captchaKey);
            response.setCaptchaImage(captchaImage);
            return Result.success(response);
        } catch (Exception e) {
            throw new RuntimeException("生成验证码失败: " + e.getMessage(), e);
        }
    }
}