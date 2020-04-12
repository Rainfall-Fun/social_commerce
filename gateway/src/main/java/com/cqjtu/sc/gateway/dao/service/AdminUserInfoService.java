package com.cqjtu.sc.gateway.dao.service;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminUserInfo;

import java.util.List;

public interface AdminUserInfoService {
    List<AdminUserInfo> findAdminUsersByUsername(String userName);
}
