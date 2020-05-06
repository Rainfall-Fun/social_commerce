package com.cqjtu.sc.gateway.dao.service;

import com.cqjtu.sc.gateway.dao.entity.wx.UserInfo;
import org.springframework.stereotype.Service;


public interface UserInfoService {
    int add(String memberNumber);
    UserInfo getByUserInfoId(int userInfoId);
    void update(UserInfo userInfo);
}
