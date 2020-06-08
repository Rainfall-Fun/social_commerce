package com.cqjtu.sc.gateway.web.admin;

import com.cqjtu.sc.gateway.annotation.RequiresPermissionsDesc;
import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermission;
import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermissions;
import com.cqjtu.sc.gateway.dao.entity.admin.AdminUserInfo;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionService;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionsService;
import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.util.admin.Permission;
import com.cqjtu.sc.gateway.util.admin.PermissionUtil;
import com.cqjtu.sc.gateway.vo.PermVo;
import com.cqjtu.sc.gateway.web.service.AuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;


@RestController
@RequestMapping("/admin/admin")
@Validated
public class AdminAdminController {

    @Autowired
    AuthService authService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminPermissionsService adminPermissionsService;
    @RequiresPermissions("admin:admin:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order) {
        return authService.adminList(username,page,limit,sort,order);
    }



    @RequiresPermissions("admin:admin:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody String admin) {
        Subject currentUser = SecurityUtils.getSubject();
        AdminUserInfo admin1 = (AdminUserInfo) currentUser.getPrincipal();
        return authService.adminCreate(admin,admin1.getOperatorId());
    }

    @RequiresPermissions("admin:admin:read")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        return authService.adminRead(id);
    }

    @RequiresPermissions("admin:admin:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody String admin) {
        Subject currentUser = SecurityUtils.getSubject();
        AdminUserInfo admin1 = (AdminUserInfo) currentUser.getPrincipal();
        return authService.adminUpdate(admin,admin1.getOperatorId());
    }

    @RequiresPermissions("admin:admin:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody String admin) {
        Subject currentUser = SecurityUtils.getSubject();
        AdminUserInfo admin1 = (AdminUserInfo) currentUser.getPrincipal();
        return authService.adminDelete(admin,admin1.getOperatorId());
    }

    @GetMapping("supplier")
    public Object supplier(){
        return authService.adminSupplier();
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

    private Set<String> getAssignedPermissions(Integer operatorId) {
        // 这里需要注意的是，如果存在超级权限*，那么这里需要转化成当前所有系统权限。
        // 之所以这么做，是因为前端不能识别超级权限，所以这里需要转换一下。
        Set<String> assignedPermissions = null;
        List<AdminPermissions> permissionsByUserInfoId = adminPermissionsService.findPermissionsByUserInfoId(operatorId);
        List<Integer> permissionsIds=new ArrayList<>();
        for (AdminPermissions adminPermissions : permissionsByUserInfoId) {
            permissionsIds.add(adminPermissions.getAuthorityId());
        }
        List<AdminPermission> permissionsByIds = adminPermissionService.findPermissionsByIds(permissionsIds);
        Set<String> permissions=new HashSet<>();
        for (AdminPermission permissionsById : permissionsByIds) {
            permissions.add(permissionsById.getAuthorityAddress());
        }
        if (permissions.contains("*")) {
            getSystemPermissions();
            assignedPermissions = systemPermissionsString;
        } else {
            assignedPermissions = permissions;
        }
        return assignedPermissions;
    }
    @RequiresPermissions("admin:admin:permission:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "权限详情")
    @PostMapping("/permissions")
    public Object updatePermissions(@RequestBody String body) {
        Subject currentUser = SecurityUtils.getSubject();
        AdminUserInfo admin1 = (AdminUserInfo) currentUser.getPrincipal();
        return authService.adminPermissionUpdate(body,admin1.getOperatorId());
    }

    /**
     * 管理员的权限情况
     *
     * @return 系统所有权限列表和管理员已分配权限
     */
    @RequiresPermissions("admin:admin:permission:get")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "更新")
    @GetMapping("/permissions")
    public Object getPermissions(Integer operatorId) {
        List<PermVo> systemPermissions = getSystemPermissions();
        Set<String> assignedPermissions = getAssignedPermissions(operatorId);

        Map<String, Object> data = new HashMap<>();
        data.put("systemPermissions", systemPermissions);
        data.put("assignedPermissions", assignedPermissions);
        return ResponseUtil.ok(data);
    }
}
