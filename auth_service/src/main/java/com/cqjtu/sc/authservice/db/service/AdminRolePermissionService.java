package com.cqjtu.sc.authservice.db.service;

import com.cqjtu.sc.authservice.db.dao.AdminActorAuthorityMapper;
import com.cqjtu.sc.authservice.db.dao.AdminAuthorityMapper;
import com.cqjtu.sc.authservice.db.domain.AdminActor;
import com.cqjtu.sc.authservice.db.domain.AdminActorAuthority;
import com.cqjtu.sc.authservice.db.domain.AdminAuthority;
import com.cqjtu.sc.authservice.db.domain.AdminAuthorityExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRolePermissionService {
    @Resource
    AdminAuthorityMapper adminAuthorityMapper;
    @Resource
    AdminActorAuthorityMapper adminActorAuthorityMapper;
    public void addPermissions(Integer roleId, List<String> permissions){
        AdminAuthorityExample example=new AdminAuthorityExample();
        AdminAuthorityExample.Criteria criteria = example.createCriteria();
        criteria.andAuthorityAddressIn(permissions);
        List<AdminAuthority> adminAuthorities = adminAuthorityMapper.selectByExample(example);
        List<AdminActorAuthority> adminActorAuthorities=new ArrayList<>();
        for (AdminAuthority adminAuthority : adminAuthorities) {
            AdminActorAuthority adminActorAuthority=new AdminActorAuthority();
            adminActorAuthority.setActorId(roleId);
            adminActorAuthority.setAuthorityId(adminAuthority.getAuthorityId());
            adminActorAuthorities.add(adminActorAuthority);
        }
        adminActorAuthorityMapper.batchInsert(adminActorAuthorities);
    }
}
