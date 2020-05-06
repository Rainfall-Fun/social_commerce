package com.cqjtu.sc.gateway.dao.service;

import com.cqjtu.sc.gateway.dao.entity.wx.UserWxInfo;

import java.util.Date;


public interface UserWxInfoService {
    UserWxInfo getUserWxInfoByOpenid(String openid);
    int add(Integer userInfoId, String openid);

}
