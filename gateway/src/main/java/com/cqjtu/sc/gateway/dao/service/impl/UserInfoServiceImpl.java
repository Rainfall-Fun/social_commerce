package com.cqjtu.sc.gateway.dao.service.impl;

import com.cqjtu.sc.gateway.dao.entity.wx.UserInfo;
import com.cqjtu.sc.gateway.dao.repo.UserInfoRepo;
import com.cqjtu.sc.gateway.dao.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoRepo userInfoRepo;
    @Override
    public int add(String memberNumber) {
        UserInfo userInfo=new UserInfo();
        if (memberNumber!=null){
            userInfo.setMemberNumber(memberNumber);
        }
        UserInfo save = userInfoRepo.save(userInfo);
        return save==null?null:save.getUserInfoId();
    }

    @Override
    public UserInfo getByUserInfoId(int userInfoId) {
        return userInfoRepo.getOne(userInfoId);
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoRepo.save(userInfo);
    }
}
