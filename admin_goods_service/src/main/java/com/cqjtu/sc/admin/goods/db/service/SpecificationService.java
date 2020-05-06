package com.cqjtu.sc.admin.goods.db.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsSpecificationMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpecificationService {
    @Resource
    AllGoodsSpecificationMapper mapper;
    public int add(AllGoodsSpecification specification){
        mapper.insertSelective(specification);
        return specification.getGoodsSpecifiId();
    }
}
