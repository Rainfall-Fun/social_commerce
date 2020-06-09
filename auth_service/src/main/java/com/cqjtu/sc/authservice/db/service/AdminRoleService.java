package com.cqjtu.sc.authservice.db.service;

import com.cqjtu.sc.authservice.db.dao.AdminActorMapper;
import com.cqjtu.sc.authservice.db.domain.AdminActor;
import com.cqjtu.sc.authservice.db.domain.AdminActorExample;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminRoleService {
    @Resource
    AdminActorMapper mapper;

    public List<AdminActor> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        AdminActorExample example=new AdminActorExample();
        AdminActorExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(name))
            criteria.andActorNameEqualTo(name);
        PageHelper.startPage(page,limit);
        example.setOrderByClause(sort+" "+order);
        List<AdminActor> adminActors = mapper.selectByExample(example);
        return adminActors;
    }

    public int create(AdminActor adminActor){
        int i = mapper.insertSelective(adminActor);
        return adminActor.getActorId();
    }

    public boolean existByRoleName(String roleName){
        AdminActorExample example=new AdminActorExample();
        AdminActorExample.Criteria criteria = example.createCriteria();
        criteria.andActorNameEqualTo(roleName);
        List<AdminActor> adminActors = mapper.selectByExample(example);
        return adminActors.size()==0?false:true;
    }

    public void updateById(AdminActor role) {
        mapper.updateByPrimaryKeySelective(role);
    }

    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public List<AdminActor> queryAll() {
        return mapper.selectByExample(new AdminActorExample());
    }
}
