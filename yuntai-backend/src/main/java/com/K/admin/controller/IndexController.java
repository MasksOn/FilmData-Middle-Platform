package com.K.admin.controller;

import com.K.admin.bean.User;
import com.K.admin.bean.UserLoginInfo;
import com.K.admin.service.IndexService;
import com.K.admin.service.UserService;
import com.K.admin.util.JwtHelper;
import com.K.admin.util.MD5;
import com.K.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/index")
public class IndexController {

    /**
     * 登录API
     * 返回值改为 Result<Object>，以兼容 String 类型的 Token 和 Map 类型的错误体
     */
    @PostMapping("login")
    public Result<Object> login(@RequestBody User userInfo) {
        System.out.println(userInfo);
        var user = UserService.getUserByName(userInfo.getUsername());

        if (user != null) {
            if (!MD5.encrypt(userInfo.getPassword()).equals(user.getPassword())) {
                return buildErrorResult("密码错误");
            } else if (user.getStatus().equals(0)) {
                return buildErrorResult("账号被禁用");
            } else {
                var token = JwtHelper.createToken(user.getId(), user.getUsername());

                // 获取当前系统时间
                Date date = new Date(System.currentTimeMillis());

                // 1. 更新内存中对象的属性 (可选)
                user.setLastLogin(date);

                // 2. 真正将时间写入数据库 (关键步！)
                UserService.updateLastLogin(user.getId(), date);

                // 成功返回，自动包裹 code=200, message="成功", data=token
                return Result.ok(token);
            }
        } else {
            return buildErrorResult("用户不存在");
        }
    }

    /**
     * 根据token获取用户信息
     */
    @GetMapping("userInfo")
    public Result<UserLoginInfo> userInfo(HttpServletRequest request) {
        var token = request.getHeader("token");
        var username = JwtHelper.getUserName(token);
        var userLoginInfo = IndexService.getUserInfo(username);

        // 替换为最新的 ok() 方法
        return Result.ok(userLoginInfo);
    }

    @PostMapping("logout")
    public Result<Void> logout() {
        // 替换为最新的 ok() 方法，无数据返回
        return Result.ok();
    }

    /**
     * 内部辅助方法：构建 { EroMessage: "错误内容" } 格式的失败返回体
     */
    private Result<Object> buildErrorResult(String errorMsg) {
        Map<String, String> errorData = new HashMap<>();
        errorData.put("EroMessage", errorMsg);

        Result<Object> result = Result.fail().message("fail");
        result.setData(errorData); // 将装有 EroMessage 的 Map 放进 data 中
        return result;
    }
}