package com.cqjtu.sc.goodsrecommendservice.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper {
    //根据销量查询
    List<Integer> queryBySale(int offset,int limit);
    //查询出本地区购买量
    List<Integer> queryByRegion(int offset,int limit,int userId);
    //根据评分查询
    List<Integer> queryByRatio(int offset,int limit);
    //查询出最近购买的三家商家的其他商品
    List<Integer> querySupplier(int offset,int limit,int userId);
    List<Integer> queryAllInOne(int userId);
    int countBySale(int limit);
    int countByRegion(int limit,int userId);
    int countByRatio(int limit);
    int countBySupplier(int limit,int userId);
}
