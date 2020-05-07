package com.cqjtu.sc.goodsrecommendservice.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<Integer> queryBySale(int limit);
    List<Integer> queryByRegion(int limit,int userId);
}
