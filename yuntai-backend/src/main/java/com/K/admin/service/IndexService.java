package com.K.admin.service;

import com.K.admin.bean.UserLoginInfo;

public class IndexService {
    public static UserLoginInfo getUserInfo(String username) {
        var user = UserService.getUserByName(username);

        // 根据用户id获取权限列表
        if (user != null) {
            var permissionsByUserId = PermissionService.getPermissionByUserId(user.getId());
            var userLoginInfo = new UserLoginInfo();
            userLoginInfo.setUsername(user.getUsername());
            userLoginInfo.getRoutes().addAll(permissionsByUserId);
            return userLoginInfo;
        }

        return null;
    }
}
