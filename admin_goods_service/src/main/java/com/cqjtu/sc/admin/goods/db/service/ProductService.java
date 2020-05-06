package com.cqjtu.sc.admin.goods.db.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsSpecifiAttValueMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiAttValue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {
    @Resource
    AllGoodsSpecifiAttValueMapper mapper;
    public void batchInsert(List<AllGoodsSpecifiAttValue> list){
        mapper.batchInsertProduct(list);
    }
}
