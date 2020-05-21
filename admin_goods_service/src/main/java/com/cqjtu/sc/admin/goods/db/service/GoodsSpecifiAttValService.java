package com.cqjtu.sc.admin.goods.db.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsSpecifiAttValueMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiAttValueExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsSpecifiAttValService {
    @Resource
    AllGoodsSpecifiAttValueMapper mapper;
    public List<AllGoodsSpecifiAttValue> queryByGoodsId(int goodsId){
        AllGoodsSpecifiAttValueExample example=new AllGoodsSpecifiAttValueExample();
        AllGoodsSpecifiAttValueExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        return mapper.selectByExample(example);
    }

    public AllGoodsSpecifiAttValue queryById(int id){
        return mapper.selectByPrimaryKey(id);
    }
}
