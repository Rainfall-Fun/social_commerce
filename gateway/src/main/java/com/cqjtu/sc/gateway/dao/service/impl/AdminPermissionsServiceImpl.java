package com.cqjtu.sc.gateway.dao.service.impl;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermissions;
import com.cqjtu.sc.gateway.dao.repo.AdminPermissionsRepo;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPermissionsServiceImpl implements AdminPermissionsService {
    @Autowired
    AdminPermissionsRepo adminPermissionsRepo;
    @Override
    public List<AdminPermissions> findPermissionsByUserInfoId(int userInfoId) {
        return adminPermissionsRepo.findByOperatorId(userInfoId);
    }
}
