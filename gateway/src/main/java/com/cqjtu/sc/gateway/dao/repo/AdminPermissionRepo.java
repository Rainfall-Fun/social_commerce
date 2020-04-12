package com.cqjtu.sc.gateway.dao.repo;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminPermissionRepo extends JpaRepository<AdminPermission,Integer> {
    List<AdminPermission> findByIdIn(List<Integer> ids);
}
