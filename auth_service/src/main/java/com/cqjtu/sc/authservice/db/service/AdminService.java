package com.cqjtu.sc.authservice.db.service;

import com.cqjtu.sc.authservice.db.dao.AdminOperatorMapper;
import com.cqjtu.sc.authservice.db.domain.AdminOperator;
import com.cqjtu.sc.authservice.db.domain.AdminOperatorExample;
import com.cqjtu.sc.authservice.vo.AdminVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    AdminOperatorMapper mapper;

    public void deleteById(Integer anotherAdminId) {
        mapper.deleteByPrimaryKey(anotherAdminId);
    }

    public int updateById(AdminOperator admin) {
        return mapper.updateByPrimaryKeySelective(admin);
    }

    public AdminOperator findById(Integer id) {

        return null;
    }

    public List<AdminOperator> findAdmin(String username) {
        AdminOperatorExample example = new AdminOperatorExample();
        AdminOperatorExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorAccountEqualTo(username);
        return mapper.selectByExample(example);
    }

    public void add(AdminOperator admin) {
        mapper.insertSelective(admin);
    }

    public List<AdminOperator> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        AdminOperatorExample example = new AdminOperatorExample();
        AdminOperatorExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(username)) {
            criteria.andOperatorAccountEqualTo(username);
        }
        PageHelper.startPage(page, limit);
        example.setOrderByClause(sort + " " + order);
        return mapper.selectByExample(example);
    }

    public List<AdminVo> queryInfo(String username, Integer page, Integer limit, String sort, String order) {
        return mapper.selectAdminInfos(username,(page-1)*limit,limit,sort,order);
    }
}
