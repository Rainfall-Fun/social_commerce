package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.AllGoodsSpecifiAttValueMapper;
import com.cqjtu.sc.orderservice.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.orderservice.db.domain.AllGoodsSpecifiAttValueExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {
    @Resource
    AllGoodsSpecifiAttValueMapper mapper;
    public List<AllGoodsSpecifiAttValue> queryInList(List<Integer> list){
        AllGoodsSpecifiAttValueExample example=new AllGoodsSpecifiAttValueExample();
        AllGoodsSpecifiAttValueExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        return mapper.selectByExample(example);
    }
}
