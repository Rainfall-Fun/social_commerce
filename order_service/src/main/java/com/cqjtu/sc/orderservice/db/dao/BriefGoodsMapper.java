package com.cqjtu.sc.orderservice.db.dao;

import com.cqjtu.sc.orderservice.db.domain.BriefGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BriefGoodsMapper {
    List<BriefGoods> getBriefGoods(List<Integer> goodsIds);
}
