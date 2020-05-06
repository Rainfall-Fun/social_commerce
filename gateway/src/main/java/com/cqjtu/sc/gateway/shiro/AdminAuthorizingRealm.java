package com.cqjtu.sc.gateway.shiro;


import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermission;
import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermissions;
import com.cqjtu.sc.gateway.dao.entity.admin.AdminUserInfo;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionService;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionsService;
import com.cqjtu.sc.gateway.dao.service.AdminUserInfoService;
import com.cqjtu.sc.gateway.util.admin.bcrypt.BCryptPasswordEncoder;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;

public class AdminAuthorizingRealm extends AuthorizingRealm {


    @Autowired
    AdminUserInfoService adminUserInfoService;
    @Autowired
    AdminPermissionsService adminPermissionsService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        AdminUserInfo admin = (AdminUserInfo) getAvailablePrincipal(principals);
        int userInfoId = admin.getOperatorId();
        List<AdminPermissions> permissionsByUserInfoId = adminPermissionsService.findPermissionsByUserInfoId(userInfoId);
        List<Integer> permissionsIds=new ArrayList<>();
        for (AdminPermissions adminPermissions : permissionsByUserInfoId) {
            permissionsIds.add(adminPermissions.getAuthorityId());
        }
        List<AdminPermission> permissionsByIds = adminPermissionService.findPermissionsByIds(permissionsIds);
        Set<String> permissions=new HashSet<>();
        for (AdminPermission permissionsById : permissionsByIds) {
            permissions.add(permissionsById.getAuthorityAddress());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());

        if (StringUtils.isEmpty(username)) {
            throw new AccountException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new AccountException("密码不能为空");
        }

        List<AdminUserInfo> adminList = adminUserInfoService.findAdminUsersByUsername(username);
        Assert.state(adminList.size() < 2, "同一个用户名存在两个账户");
        if (adminList.size() == 0) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }
        AdminUserInfo admin = adminList.get(0);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, admin.getOperatorPassword())) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }
        return new SimpleAuthenticationInfo(admin, password, getName());
    }

}
