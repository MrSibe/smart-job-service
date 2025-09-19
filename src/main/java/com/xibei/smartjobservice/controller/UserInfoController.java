package com.xibei.smartjobservice.controller;

import com.xibei.smartjobservice.util.JwtUtils;
import com.xibei.smartjobservice.model.dto.Result;
import com.xibei.smartjobservice.service.PermissionService;
import com.xibei.smartjobservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息控制器
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserInfoController {

    private final JwtUtils jwtUtils;
    private final RoleService roleService;
    private final PermissionService permissionService;

    /**
     * 获取当前用户信息
     */
    @GetMapping("/me")
    public Result<Map<String, Object>> getCurrentUser(HttpServletRequest request) {
        String authHeader = request.getHeader(jwtUtils.getTokenHeader());
        if (authHeader != null && authHeader.startsWith(jwtUtils.getTokenPrefix())) {
            String token = authHeader.substring(jwtUtils.getTokenPrefix().length()).trim();

            if (jwtUtils.validateToken(token)) {
                Long userId = jwtUtils.getUserIdFromToken(token);
                String phone = jwtUtils.getPhoneFromToken(token);

                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("id", userId);
                userInfo.put("phone", phone);
                userInfo.put("enabled", true);

                // 获取用户角色
                userInfo.put("roles", roleService.getRolesByUserId(userId).stream()
                        .map(role -> role.getName())
                        .collect(java.util.stream.Collectors.toList()));

                // 获取用户权限
                userInfo.put("permissions", permissionService.getPermissionsByUserId(userId));

                return Result.success(userInfo);
            }
        }

        return Result.error("401", "未认证");
    }
}