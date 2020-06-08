package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.AllGoodsSpecifiAttValueMapper;
import com.cqjtu.sc.orderservice.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.orderservice.db.domain.AllGoodsSpecifiAttValueExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取list中id的商品数量
     * @param ids
     * @return
     */
    public Map<Integer,Integer> getNumbersInList(List<Integer> ids){
        AllGoodsSpecifiAttValue.Column column[]={AllGoodsSpecifiAttValue.Column.number, AllGoodsSpecifiAttValue.Column.id};
        Map<Integer,Integer> numberMap=new HashMap<>();
        AllGoodsSpecifiAttValueExample example=new AllGoodsSpecifiAttValueExample();
        AllGoodsSpecifiAttValueExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        List<AllGoodsSpecifiAttValue> allGoodsSpecifiAttValues = mapper.selectByExampleSelective(example, column);
        for (AllGoodsSpecifiAttValue allGoodsSpecifiAttValue : allGoodsSpecifiAttValues) {
            numberMap.put(allGoodsSpecifiAttValue.getId(),allGoodsSpecifiAttValue.getNumber());
        }

        return numberMap;
    }

    public int addNumber(Integer productId, Integer number) {
        Integer integer = mapper.addNumber(number,productId);
        return integer;
    }
}
