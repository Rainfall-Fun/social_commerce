package com.cqjtu.sc.admin.goods.service;

import com.cqjtu.sc.admin.goods.db.dao.AllCarrigeRuleMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllCarrigeRule;
import com.cqjtu.sc.admin.goods.db.domain.AllCarrigeRuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreightRuleService {
    /**
     * 得到所有的运费规则
     */
    @Autowired
    AllCarrigeRuleMapper freightRuleMapper;

    public List<AllCarrigeRule> getAllFreightRule() {
        return freightRuleMapper.selectByExample(new AllCarrigeRuleExample());
    }
}
