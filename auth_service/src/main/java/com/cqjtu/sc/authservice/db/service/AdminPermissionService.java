package com.cqjtu.sc.authservice.db.service;

import com.cqjtu.sc.authservice.db.dao.AdminAuthorityMapper;
import com.cqjtu.sc.authservice.db.dao.AdminOperatorAuthorityMapper;
import com.cqjtu.sc.authservice.db.domain.AdminActorAuthority;
import com.cqjtu.sc.authservice.db.domain.AdminAuthority;
import com.cqjtu.sc.authservice.db.domain.AdminAuthorityExample;
import com.cqjtu.sc.authservice.db.domain.AdminOperatorAuthority;
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
        List<AdminOperatorAuthority> adminActorAuthorities=new ArrayList<>();
        for (AdminAuthority adminAuthority : adminAuthorities) {
            AdminOperatorAuthority adminActorAuthority=new AdminOperatorAuthority();
            adminActorAuthority.setAuthorityId(adminAuthority.getAuthorityId());
            adminActorAuthority.setOperatorId(operatorId);
            adminActorAuthorities.add(adminActorAuthority);
        }
        mapper.batchInsert(adminActorAuthorities);
    }
}
