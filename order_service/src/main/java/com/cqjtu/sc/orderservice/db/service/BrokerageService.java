package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.BrokerageMapper;
import com.cqjtu.sc.orderservice.db.domain.Brokerage;
import com.cqjtu.sc.orderservice.db.domain.BrokerageExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BrokerageService {
    @Resource
    BrokerageMapper mapper;

    public Brokerage findBuUserId(int uid) {
        BrokerageExample example = new BrokerageExample();
        BrokerageExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(uid);
        List<Brokerage> brokerages = mapper.selectByExample(example);
        if (brokerages == null || brokerages.size() == 0) {
            Brokerage brokerage = new Brokerage();
            brokerage.setGainedBrokerage(BigDecimal.valueOf(0));
            brokerage.setGeneratedBrokerage(BigDecimal.valueOf(0));
            return brokerage;
        }
        return brokerages.get(0);
    }
}
