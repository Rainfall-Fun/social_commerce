package com.cqjtu.sc.gateway.dao.service.impl;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermission;
import com.cqjtu.sc.gateway.dao.repo.AdminPermissionRepo;
import com.cqjtu.sc.gateway.dao.service.AdminPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {
    @Autowired
    AdminPermissionRepo adminPermissionRepo;
    @Override
    public List<AdminPermission> findPermissionsByIds(List<Integer> ids) {
        return adminPermissionRepo.findByAuthorityIdIn(ids);
    }
}
