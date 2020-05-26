package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.AllGoodsSpecificationMapper;
import com.cqjtu.sc.orderservice.db.domain.AllGoodsSpecification;
import com.cqjtu.sc.orderservice.db.domain.AllGoodsSpecificationExample;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsSpecificationService {
    @Resource
    AllGoodsSpecificationMapper mapper;
    public List<AllGoodsSpecification> queryInList(List<Integer> ids){
        AllGoodsSpecificationExample example=new AllGoodsSpecificationExample();
        AllGoodsSpecificationExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsSpecifiIdIn(ids);
        return mapper.selectByExample(example);
    }
}
