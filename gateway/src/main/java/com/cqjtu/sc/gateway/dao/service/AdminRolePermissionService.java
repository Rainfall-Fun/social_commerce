package com.cqjtu.sc.gateway.dao.service;

import java.util.Set;

public interface AdminRolePermissionService {
    Set<String> queryStringByRoleId(int roleId);
}
