package com.cqjtu.sc.admin.goods.db.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsInfoMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AllGoodsInfoService {
    @Resource
    AllGoodsInfoMapper mapper;
    public int add(AllGoodsInfo allGoodsInfo){
        mapper.insertSelective(allGoodsInfo);
        return allGoodsInfo.getGoodsId();
    }

    public AllGoodsInfo findById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

}
