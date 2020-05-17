package com.cqjtu.sc.admin.goods.db.dao;

import com.cqjtu.sc.admin.goods.dto.BriefGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BriefGoodsMapper {
    List<BriefGoods> getBriefGoods(List<Integer> goodsIds);
}
