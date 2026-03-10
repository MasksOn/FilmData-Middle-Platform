package com.K.admin.controller;

import com.K.admin.bean.Permission;
import com.K.admin.bean.Role;
import com.K.admin.bean.RolePermission;
import com.K.admin.service.RolePermissionService;
import com.K.admin.service.RoleService;
import com.K.util.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    /**
     * 获取所有角色
     */
    @GetMapping("getAllRoles")
    public Result<List<Role>> getAllRoles() {
        return Result.of(200, "success", RoleService.getAllRoles());
    }

    /**
     * 根据角色ID删除角色
     */
    @DeleteMapping("deleteRole/{roleId}")
    public Result<String> deleteRoleById(@PathVariable Long roleId) {
        RoleService.deleteRole(roleId);
        return Result.of(200, "success", "删除角色成功");
    }

    /**
     * 添加新角色
     */
    @PostMapping("addRole")
    public Result<String> addRole(@RequestParam String roleName) {
        RoleService.addRole(roleName);
        return Result.of(200, "success", "添加新角色成功");
    }

    /**
     * 根据角色ID获取菜单
     */
    @GetMapping("permissions/{roleId}")
    public Result<List<Permission>> getPermissionsByRoleId(@PathVariable Long roleId) {
        return Result.of(200, "success", RolePermissionService.getPermissionsByRoleId(roleId));
    }

    /**
     * 为角色分配权限
     */
    @PostMapping("assignPermissionsToRole")
    public Result<String> assignPermissionsToRole(@RequestBody RolePermission rolePermission) {
        RolePermissionService.addRolePermissions(
                rolePermission.getRoleId(),
                Arrays.stream(rolePermission.getPermissionIds().split(",")).map(Long::parseLong).collect(Collectors.toList())
        );
        return Result.of(200, "success", "分配成功");
    }
    /**
     * 分页 + 条件获取角色列表
     */
    @GetMapping("{page}/{limit}")
    public Result<java.util.Map<String, Object>> getPageList(
            @PathVariable int page,
            @PathVariable int limit,
            @RequestParam(required = false) String roleName) {

        // 调用 Service 获取分页数据
        java.util.Map<String, Object> map = RoleService.getPageList(page, limit, roleName);
        return Result.of(200, "success", map);
    }
}
