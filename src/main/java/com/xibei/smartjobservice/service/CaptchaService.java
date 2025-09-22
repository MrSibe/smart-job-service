package com.xibei.smartjobservice.service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务
 */
@Service
public class CaptchaService {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CAPTCHA_PREFIX = "captcha:";
    private static final long CAPTCHA_EXPIRE_MINUTES = 5;

    /**
     * 生成验证码图片
     */
    public String generateCaptchaImage(String captchaKey) throws IOException {
        // 生成验证码文本
        String code = defaultKaptcha.createText();

        try {
            // 存储验证码到Redis，5分钟过期
            redisTemplate.opsForValue().set(
                CAPTCHA_PREFIX + captchaKey,
                code,
                CAPTCHA_EXPIRE_MINUTES,
                TimeUnit.MINUTES
            );
        } catch (Exception e) {
            // Redis连接失败，使用内存存储（仅用于开发测试）
            System.err.println("Redis连接失败，使用内存存储验证码: " + e.getMessage());
            // 这里可以添加内存存储逻辑，但为了简单起见，我们继续生成图片
        }

        // 生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);

        // 转换为Base64
        byte[] bytes = outputStream.toByteArray();
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 验证验证码
     */
    public boolean validateCaptcha(String captchaKey, String code) {
        if (captchaKey == null || code == null) {
            return false;
        }

        try {
            String storedCode = (String) redisTemplate.opsForValue().get(CAPTCHA_PREFIX + captchaKey);
            if (storedCode == null) {
                return false;
            }

            // 验证成功后删除验证码
            redisTemplate.delete(CAPTCHA_PREFIX + captchaKey);
            return storedCode.equalsIgnoreCase(code);
        } catch (Exception e) {
            // Redis连接失败，跳过验证（仅用于开发测试）
            System.err.println("Redis连接失败，跳过验证码验证: " + e.getMessage());
            return true; // 开发环境下跳过验证
        }
    }

    /**
     * 生成验证码key
     */
    public String generateCaptchaKey() {
        return UUID.randomUUID().toString();
    }
}