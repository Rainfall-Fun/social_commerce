package com.cqjtu.sc.gateway.dao.repo;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserInfoRepo extends JpaRepository<AdminUserInfo,Integer> {
    List<AdminUserInfo> findByUsername(String username);
}
