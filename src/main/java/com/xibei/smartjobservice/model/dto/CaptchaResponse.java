package com.xibei.smartjobservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 验证码响应
 */
@Data
@Schema(description = "验证码响应")
public class CaptchaResponse {

    @Schema(description = "验证码key，用于后续验证")
    private String captchaKey;

    @Schema(description = "Base64编码的验证码图片")
    private String captchaImage;
}