package com.cqjtu.sc.gateway.web.admin;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermission;
import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermissions;
import com.cqjtu.sc.gateway.dao.entity.admin.AdminUserInfo;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionService;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionsService;
import com.cqjtu.sc.gateway.util.JacksonUtil;
import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.util.admin.Permission;
import com.cqjtu.sc.gateway.util.admin.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

import static com.cqjtu.sc.gateway.util.admin.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

@RestController
@RequestMapping("/admin/auth")
@Validated
public class AdminAuthController {
    @RequestMapping("auth")
    public String auth(){
        return "aaa";
    }

    @RequestMapping("auth/a")
    public String auth1(){
        return "aaa1";
    }

    @PostMapping("/login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseUtil.badArgument();
        }
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException uae) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号或密码不正确");
        } catch (LockedAccountException lae) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号已锁定不可用");

        } catch (AuthenticationException ae) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "认证失败");
        }

        currentUser = SecurityUtils.getSubject();
        AdminUserInfo admin = (AdminUserInfo) currentUser.getPrincipal();

        // userInfo
        Map<String, Object> adminInfo = new HashMap<String, Object>();
        adminInfo.put("nickName", admin.getOperatorAccount());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", currentUser.getSession().getId());
        result.put("adminInfo", adminInfo);
        return ResponseUtil.ok(result);
    }

    @Autowired
    AdminPermissionsService adminPermissionsService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @RequiresAuthentication
    @GetMapping("/info")
    public Object info() {
        Subject currentUser = SecurityUtils.getSubject();
        AdminUserInfo admin = (AdminUserInfo) currentUser.getPrincipal();

        Map<String, Object> data = new HashMap<>();
        data.put("name", admin.getOperatorAccount());

        List<AdminPermissions> permissionsByUserInfoId = adminPermissionsService.findPermissionsByUserInfoId(admin.getOperatorId());
        List<Integer> permissionsIds=new ArrayList<>();
        for (AdminPermissions adminPermissions : permissionsByUserInfoId) {
            permissionsIds.add(adminPermissions.getAuthorityId());
        }
        List<AdminPermission> permissionsByIds = adminPermissionService.findPermissionsByIds(permissionsIds);
        Set<String> permissions=new HashSet<>();
        for (AdminPermission permissionsById : permissionsByIds) {
            permissions.add(permissionsById.getAuthorityAddress());
        }
        // NOTE
        // 这里需要转换perms结构，因为对于前端而已API形式的权限更容易理解
        data.put("perms", toApi(permissions));
        return ResponseUtil.ok(data);
    }

    @Autowired
    private ApplicationContext context;
    private HashMap<String, String> systemPermissionsMap = null;

    private Collection<String> toApi(Set<String> permissions) {
        if (systemPermissionsMap == null) {
            systemPermissionsMap = new HashMap<>();
            final String basicPackage = "com.cqjtu.sc.gateway.web";
            List<Permission> systemPermissions = PermissionUtil.listPermission(context, basicPackage);
            for (Permission permission : systemPermissions) {
                String perm = permission.getRequiresPermissions().value()[0];
                String api = permission.getApi();
                systemPermissionsMap.put(perm, api);
            }
        }

        Collection<String> apis = new HashSet<>();
        for (String perm : permissions) {
            String api = systemPermissionsMap.get(perm);
            apis.add(api);

            if (perm.equals("*")) {
                apis.clear();
                apis.add("*");
                return apis;
            }
        }
        return apis;
    }

}
