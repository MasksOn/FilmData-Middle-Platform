package com.K.admin.controller;

import com.K.admin.bean.User;
import com.K.admin.bean.UserRole;
import com.K.admin.service.UserRoleService;
import com.K.admin.service.UserService;
import com.K.admin.util.MD5;
import com.K.util.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    /**
     * 获取所有用户
     */
    @GetMapping("getAllUsers")
    public Result<List<User>> getAllUsers() {
        return Result.of(200, "success", UserService.getAllUsers());
    }

    /**
     * 根据用户ID获取用户信息
     */
    @GetMapping("getUser/{userId}")
    public Result<User> getUserById(@PathVariable Long userId) {
        return Result.of(200, "success", UserService.getUserById(userId));
    }

    /**
     * 新增用户 (增加了参数校验拦截)
     */
    @PostMapping("addUser")
    public Result<Object> addUser(@RequestBody User userInfo) {
        // ==========================================
        // 🚀 后端拦截校验：防空指针及长度限制
        // ==========================================
        if (userInfo.getUsername() == null || userInfo.getUsername().trim().length() < 3) {
            return buildErrorResult( "用户名不少于3个字符");
        }
        if (userInfo.getPassword() == null || userInfo.getPassword().length() < 6) {
            return buildErrorResult("密码不少于6个字符");
        }

        // 将密码加密后重新设置回 userInfo 对象
        userInfo.setPassword(MD5.encrypt(userInfo.getPassword()));

        // 直接传递整个对象给 Service 层
        UserService.addUser(userInfo);

        return Result.of(200, "success", "新添加了一个用户");
    }

    /**
     * 删除用户
     */
    @DeleteMapping("deleteUser/{userId}")
    public Result<String> deleteUser(@PathVariable Long userId) {
        UserService.deleteUserById(userId);
        return Result.of(200, "success", "删除用户完毕");
    }

    /**
     * 根据用户ID获取角色ID
     */
    @GetMapping("getRoleIdByUserId/{userId}")
    public Result<Long> getRoleIdByUserId(@PathVariable Long userId) {
        return Result.of(200, "success", UserRoleService.getRoleIdByUserId(userId));
    }

    /**
     * 为用户分配角色
     */
    @PostMapping("assignRoleToUser")
    public Result<String> assignRoleToUser(@RequestBody UserRole userRole) {
        UserRoleService.addUserIdRoleIdRelationship(userRole.getUserId(), userRole.getRoleId());
        return Result.of(200, "success", "分配角色成功");
    }

    /**
     * 修改用户信息
     */
    @PostMapping("updateUser")
    public Result<String> updateUser(@RequestBody User userInfo) {
        // 调用 Service 层执行更新
        UserService.updateUser(userInfo);
        return Result.of(200, "success", "修改用户信息成功");
    }

    /**
     * 更新用户状态 (启用/禁用)
     */
    @GetMapping("updateStatus/{id}/{status}")
    public Result<String> updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        // 调用 Service 层的 updateStatus 方法
        UserService.updateStatus(id, status);
        return Result.of(200, "success", "状态更新成功");
    }

    /**
     * 分页 + 条件获取用户列表
     */
    @GetMapping("{page}/{limit}")
    public Result<Map<String, Object>> getPageList(
            @PathVariable int page,
            @PathVariable int limit,
            @RequestParam(required = false) String username) {

        // 调用 Service 获取分页数据
        Map<String, Object> map = UserService.getPageList(page, limit, username);
        return Result.of(200, "success", map);
    }

    private Result<Object> buildErrorResult(String errorMsg) {
        Map<String, String> errorData = new HashMap<>();
        errorData.put("EroMessage", errorMsg);

        Result<Object> result = Result.fail().message("fail");
        result.setData(errorData); // 将装有 EroMessage 的 Map 放进 data 中
        return result;
    }
}