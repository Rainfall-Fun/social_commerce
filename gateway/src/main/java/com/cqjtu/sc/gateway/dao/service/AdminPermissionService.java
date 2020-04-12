package com.cqjtu.sc.gateway.dao.service;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermission;
import java.util.List;

public interface AdminPermissionService {
    List<AdminPermission> findPermissionsByIds(List<Integer> ids);
}
