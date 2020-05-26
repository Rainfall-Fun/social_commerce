package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.AllGoodsSpecifiAttValueMapper;
import com.cqjtu.sc.orderservice.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.orderservice.db.domain.AllGoodsSpecifiAttValueExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean reduceNumber(Integer id,int number) throws Exception {
        Integer numberById = mapper.findNumberById(id);
        if (numberById>=number){
            AllGoodsSpecifiAttValue allGoodsSpecifiAttValue=new AllGoodsSpecifiAttValue();
            allGoodsSpecifiAttValue.setId(id);
            allGoodsSpecifiAttValue.setNumber(numberById-number);
            mapper.updateByPrimaryKeySelective(allGoodsSpecifiAttValue);
            return true;
        }
        return false;
    }
}
