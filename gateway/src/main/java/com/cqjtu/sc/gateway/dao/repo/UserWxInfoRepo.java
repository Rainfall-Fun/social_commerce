package com.cqjtu.sc.gateway.dao.repo;


import com.cqjtu.sc.gateway.dao.entity.wx.UserWxInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWxInfoRepo extends JpaRepository<UserWxInfo,Integer> {
    UserWxInfo findTopByOpenid(String openid);
}
