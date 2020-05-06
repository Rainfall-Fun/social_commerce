package com.cqjtu.sc.gateway.dao.service.impl;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminUserInfo;
import com.cqjtu.sc.gateway.dao.repo.AdminUserInfoRepo;
import com.cqjtu.sc.gateway.dao.service.AdminUserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserInfoServiceImpl implements AdminUserInfoService {

    @Autowired
    AdminUserInfoRepo userInfoRepo;
    @Override
    public List<AdminUserInfo> findAdminUsersByUsername(String userName) {
        if (StringUtils.isBlank(userName))
            return null;
        return userInfoRepo.findByOperatorAccount(userName);
    }
}
