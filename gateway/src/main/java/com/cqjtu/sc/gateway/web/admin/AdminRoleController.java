package com.cqjtu.sc.gateway.web.admin;

import com.cqjtu.sc.gateway.annotation.RequiresPermissionsDesc;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionService;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionsService;
import com.cqjtu.sc.gateway.dao.service.AdminRolePermissionService;
import com.cqjtu.sc.gateway.util.JacksonUtil;
import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.util.admin.Permission;
import com.cqjtu.sc.gateway.util.admin.PermissionUtil;
import com.cqjtu.sc.gateway.vo.PermVo;
import com.cqjtu.sc.gateway.web.service.AuthService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.annotations.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;



@RestController
@RequestMapping("/admin/role")
@Validated
public class AdminRoleController {


    @Autowired
    private AdminRolePermissionService permissionService;
    @Autowired
    private AuthService authService;

    @RequiresPermissions("admin:role:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order) {
        return authService.querySelective(name, page, limit, sort, order);
    }


    @RequiresPermissions("admin:role:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色添加")
    @PostMapping("/create")
    public Object create(@RequestBody String body) {
        return authService.createRole(body);
    }


    @PostMapping("/update")
    public Object update(@RequestBody String role) {
        return authService.updateRole(role);
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody String role) {
        return authService.deleteRole(role);
    }




    @Autowired
    private ApplicationContext context;
    private List<PermVo> systemPermissions = null;
    private Set<String> systemPermissionsString = null;

    private List<PermVo> getSystemPermissions() {
        final String basicPackage = "com.cqjtu.sc.gateway.web";
        if (systemPermissions == null) {
            List<Permission> permissions = PermissionUtil.listPermission(context, basicPackage);
            systemPermissions = PermissionUtil.listPermVo(permissions);
            systemPermissionsString = PermissionUtil.listPermissionString(permissions);
        }
        return systemPermissions;
    }

    private Set<String> getAssignedPermissions(Integer roleId) {
        // 这里需要注意的是，如果存在超级权限*，那么这里需要转化成当前所有系统权限。
        // 之所以这么做，是因为前端不能识别超级权限，所以这里需要转换一下。
        Set<String> assignedPermissions = null;
        Set<String> permissions = permissionService.queryStringByRoleId(roleId);
        if (permissions.contains("*")) {
            getSystemPermissions();
            assignedPermissions = systemPermissionsString;
        } else {
            assignedPermissions = permissions;
        }
        return assignedPermissions;
    }

    /**
     * 管理员的权限情况
     *
     * @return 系统所有权限列表和管理员已分配权限
     */
    @RequiresPermissions("admin:role:permission:get")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "权限详情")
    @GetMapping("/permissions")
    public Object getPermissions(Integer roleId) {
        List<PermVo> systemPermissions = getSystemPermissions();
        Set<String> assignedPermissions = getAssignedPermissions(roleId);

        Map<String, Object> data = new HashMap<>();
        data.put("systemPermissions", systemPermissions);
        data.put("assignedPermissions", assignedPermissions);
        return ResponseUtil.ok(data);
    }


    @PostMapping("/permissions")
    public Object updatePermissions(@RequestBody String body) {
        return authService.updateRolePermission(body);
    }

    @GetMapping("/options")
    public Object options() {
        return authService.roleOption();
    }


}
