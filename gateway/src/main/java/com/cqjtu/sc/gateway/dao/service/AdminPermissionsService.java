package com.cqjtu.sc.gateway.dao.service;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermissions;

import java.util.List;

public interface AdminPermissionsService {
    List<AdminPermissions> findPermissionsByUserInfoId(int userInfoId);
}
