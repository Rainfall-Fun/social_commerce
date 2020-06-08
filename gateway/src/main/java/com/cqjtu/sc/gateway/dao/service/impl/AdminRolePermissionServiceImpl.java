package com.cqjtu.sc.gateway.dao.service.impl;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermission;
import com.cqjtu.sc.gateway.dao.entity.admin.AdminRolePermissions;
import com.cqjtu.sc.gateway.dao.repo.AdminPermissionRepo;
import com.cqjtu.sc.gateway.dao.repo.AdminRolePermissionRepo;
import com.cqjtu.sc.gateway.dao.service.AdminRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class AdminRolePermissionServiceImpl implements AdminRolePermissionService {
    @Autowired
    AdminRolePermissionRepo repo;
    @Autowired
    AdminPermissionRepo adminPermissionRepo;
    @Override
    public Set<String> queryStringByRoleId(int roleId) {
        List<AdminRolePermissions> byActorId = repo.findByActorId(roleId);
        Set<String> permissions=new HashSet<>();
        List<Integer> permissionId=new ArrayList<>();
        for (AdminRolePermissions adminRolePermissions : byActorId) {
            permissionId.add(adminRolePermissions.getAuthorityId());
        }
        List<AdminPermission> byAuthorityIdIn = adminPermissionRepo.findByAuthorityIdIn(permissionId);
        for (AdminPermission adminPermission : byAuthorityIdIn) {
            permissions.add(adminPermission.getAuthorityAddress());
        }
        return permissions;
    }
}
