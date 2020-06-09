package com.cqjtu.sc.authservice.db.service;

import com.cqjtu.sc.authservice.db.dao.AdminAuthorityMapper;
import com.cqjtu.sc.authservice.db.dao.AdminOperatorAuthorityMapper;
import com.cqjtu.sc.authservice.db.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminPermissionService {
    @Resource
    AdminOperatorAuthorityMapper mapper;
    @Resource
    AdminAuthorityMapper adminAuthorityMapper;
    public void addPermissions(Integer operatorId, List<String> permissions){
        AdminAuthorityExample example=new AdminAuthorityExample();
        AdminAuthorityExample.Criteria criteria = example.createCriteria();
        criteria.andAuthorityAddressIn(permissions);
        List<AdminAuthority> adminAuthorities = adminAuthorityMapper.selectByExample(example);

        //先删除，再添加
        AdminOperatorAuthorityExample adminOperatorAuthorityExample=new AdminOperatorAuthorityExample();
        AdminOperatorAuthorityExample.Criteria criteria1 = adminOperatorAuthorityExample.createCriteria();
        criteria1.andOperatorIdEqualTo(operatorId);
        mapper.deleteByExample(adminOperatorAuthorityExample);
        List<AdminOperatorAuthority> adminActorAuthorities=new ArrayList<>();
        for (AdminAuthority adminAuthority : adminAuthorities) {
            AdminOperatorAuthority adminActorAuthority=new AdminOperatorAuthority();
            adminActorAuthority.setAuthorityId(adminAuthority.getAuthorityId());
            adminActorAuthority.setOperatorId(operatorId);
            adminActorAuthorities.add(adminActorAuthority);
        }
        mapper.batchInsert(adminActorAuthorities);
    }

    public void addPermissionsByPid(Integer operatorId,List<Integer> permissionId){
        List<AdminOperatorAuthority> adminActorAuthorities=new ArrayList<>();
        for (Integer pid : permissionId) {
            AdminOperatorAuthority adminActorAuthority=new AdminOperatorAuthority();
            adminActorAuthority.setAuthorityId(pid);
            adminActorAuthority.setOperatorId(operatorId);
            adminActorAuthorities.add(adminActorAuthority);
        }
        mapper.batchInsert(adminActorAuthorities);
    }
}
