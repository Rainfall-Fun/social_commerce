package com.cqjtu.sc.gateway.dao.service.impl;

import com.cqjtu.sc.gateway.dao.entity.wx.UserWxInfo;
import com.cqjtu.sc.gateway.dao.repo.UserWxInfoRepo;
import com.cqjtu.sc.gateway.dao.service.UserWxInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserWxInfoServiceImpl implements UserWxInfoService {
    @Autowired
    UserWxInfoRepo userWxInfoRepo;
    @Override
    public UserWxInfo getUserWxInfoByOpenid(String openid) {
        return userWxInfoRepo.findTopByOpenid(openid);
    }

    @Override
    public int add(Integer userInfoId, String openid) {
        UserWxInfo userWxInfo=new UserWxInfo();
        if (userInfoId!=null){
            userWxInfo.setUserInfoId(userInfoId);
        }
        if(!StringUtils.isBlank(openid)){
            userWxInfo.setOpenid(openid);
        }
        UserWxInfo save = userWxInfoRepo.save(userWxInfo);
        return save==null?null:save.getUserInfoId();
    }
}
