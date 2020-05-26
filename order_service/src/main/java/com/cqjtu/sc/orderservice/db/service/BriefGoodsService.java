package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.BriefGoodsMapper;
import com.cqjtu.sc.orderservice.db.domain.BriefGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BriefGoodsService {
    @Resource
    BriefGoodsMapper mapper;
    public List<BriefGoods> getBriefGoods(List<Integer> goodsIds){
        return mapper.getBriefGoods(goodsIds);
    }
}
