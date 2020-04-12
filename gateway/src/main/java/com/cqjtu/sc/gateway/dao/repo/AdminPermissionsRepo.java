package com.cqjtu.sc.gateway.dao.repo;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminPermissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminPermissionsRepo extends JpaRepository<AdminPermissions,Integer> {
    List<AdminPermissions> findByUserInfoId(int userInfoId);
}
