package com.cqjtu.sc.gateway.dao.repo;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminRolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRolePermissionRepo extends JpaRepository<AdminRolePermissions,Integer> {
    List<AdminRolePermissions> findByActorId(int id);
}
