package com.cqjtu.sc.gateway.dao.service;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermissions;

import java.util.List;
import java.util.Set;

public interface AdminPermissionsService {
    List<AdminPermissions> findPermissionsByUserInfoId(int userInfoId);
    Set<String> queryByRoleId(Integer roleId);
}
