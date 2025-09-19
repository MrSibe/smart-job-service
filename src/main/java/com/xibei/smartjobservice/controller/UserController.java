package com.xibei.smartjobservice.controller;

import com.xibei.smartjobservice.model.dto.Result;
import com.xibei.smartjobservice.model.entity.SysUser;
import com.xibei.smartjobservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 获取所有用户列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('user:list')")
    public Result<List<SysUser>> getAllUsers() {
        List<SysUser> users = userService.getAllUsers();
        return Result.success(users);
    }

    /**
     * 根据ID获取用户
     */
    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('user:list')")
    public Result<SysUser> getUserById(@PathVariable Long userId) {
        SysUser user = userService.getUserById(userId);
        return Result.success(user);
    }

    /**
     * 创建用户
     */
    @PostMapping
    @PreAuthorize("hasAuthority('user:create')")
    public Result<String> createUser(@RequestBody SysUser user) {
        boolean success = userService.createUser(user);
        return success ? Result.success("用户创建成功") : Result.error("用户创建失败");
    }

    /**
     * 更新用户
     */
    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('user:update')")
    public Result<String> updateUser(@PathVariable Long userId, @RequestBody SysUser user) {
        user.setId(userId);
        boolean success = userService.updateUser(user);
        return success ? Result.success("用户更新成功") : Result.error("用户更新失败");
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('user:delete')")
    public Result<String> deleteUser(@PathVariable Long userId) {
        boolean success = userService.deleteUser(userId);
        return success ? Result.success("用户删除成功") : Result.error("用户删除失败");
    }

    /**
     * 启用用户
     */
    @PostMapping("/{userId}/enable")
    @PreAuthorize("hasAuthority('user:update')")
    public Result<String> enableUser(@PathVariable Long userId) {
        boolean success = userService.enableUser(userId);
        return success ? Result.success("用户启用成功") : Result.error("用户启用失败");
    }

    /**
     * 禁用用户
     */
    @PostMapping("/{userId}/disable")
    @PreAuthorize("hasAuthority('user:update')")
    public Result<String> disableUser(@PathVariable Long userId) {
        boolean success = userService.disableUser(userId);
        return success ? Result.success("用户禁用成功") : Result.error("用户禁用失败");
    }

    /**
     * 修改密码
     */
    @PostMapping("/{userId}/password")
    @PreAuthorize("hasAuthority('user:update')")
    public Result<String> changePassword(@PathVariable Long userId, @RequestParam String newPassword) {
        boolean success = userService.changePassword(userId, newPassword);
        return success ? Result.success("密码修改成功") : Result.error("密码修改失败");
    }
}