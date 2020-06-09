package com.cqjtu.sc.authservice.db.service;

import com.cqjtu.sc.authservice.db.dao.AdminActorAuthorityMapper;
import com.cqjtu.sc.authservice.db.dao.AdminAuthorityMapper;
import com.cqjtu.sc.authservice.db.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRolePermissionService {
    @Resource
    AdminAuthorityMapper adminAuthorityMapper;
    @Resource
    AdminActorAuthorityMapper adminActorAuthorityMapper;
    @Transactional
    public void addPermissions(Integer roleId, List<String> permissions){
        AdminAuthorityExample example=new AdminAuthorityExample();
        AdminAuthorityExample.Criteria criteria = example.createCriteria();
        criteria.andAuthorityAddressIn(permissions);
        List<AdminAuthority> adminAuthorities = adminAuthorityMapper.selectByExample(example);

        //先删除，再添加
        AdminActorAuthorityExample adminActorAuthorityExample=new AdminActorAuthorityExample();
        AdminActorAuthorityExample.Criteria criteria1 = adminActorAuthorityExample.createCriteria();
        criteria1.andActorIdEqualTo(roleId);
        adminActorAuthorityMapper.deleteByExample(adminActorAuthorityExample);
        List<AdminActorAuthority> adminActorAuthorities=new ArrayList<>();
        for (AdminAuthority adminAuthority : adminAuthorities) {
            AdminActorAuthority adminActorAuthority=new AdminActorAuthority();
            adminActorAuthority.setActorId(roleId);
            adminActorAuthority.setAuthorityId(adminAuthority.getAuthorityId());
            adminActorAuthorities.add(adminActorAuthority);
        }
        adminActorAuthorityMapper.batchInsert(adminActorAuthorities);
    }

    public List<AdminActorAuthority> findByActorId(int actorId){
        AdminActorAuthorityExample example=new AdminActorAuthorityExample();
        AdminActorAuthorityExample.Criteria criteria = example.createCriteria();
        criteria.andActorIdEqualTo(actorId);
        return adminActorAuthorityMapper.selectByExample(example);
    }
}
