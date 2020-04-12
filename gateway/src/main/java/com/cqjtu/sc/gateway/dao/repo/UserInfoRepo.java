package com.cqjtu.sc.gateway.dao.repo;

import com.cqjtu.sc.gateway.dao.entity.wx.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo,Integer> {
}
