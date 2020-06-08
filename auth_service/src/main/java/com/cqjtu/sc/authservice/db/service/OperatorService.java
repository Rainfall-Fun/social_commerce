package com.cqjtu.sc.authservice.db.service;

import com.cqjtu.sc.authservice.db.dao.AdminOperatorMapper;
import com.cqjtu.sc.authservice.db.domain.AdminOperator;
import com.cqjtu.sc.authservice.db.domain.AdminOperatorExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OperatorService {
    @Resource
    AdminOperatorMapper mapper;
    public int countByRoleId(int roleId){
        AdminOperatorExample example=new AdminOperatorExample();
        AdminOperatorExample.Criteria criteria = example.createCriteria();
        criteria.andActorIdEqualTo(roleId);
        List<AdminOperator> adminOperators = mapper.selectByExample(example);
        return adminOperators.size();
    }
}
