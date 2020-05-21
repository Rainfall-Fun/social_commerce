package com.cqjtu.sc.goodsrecommendservice.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper {
    List<Integer> queryBySale(int offset,int limit);
    List<Integer> queryByRegion(int offset,int limit,int userId);
    List<Integer> queryByRatio(int offset,int limit);
    List<Integer> querySupplier(int offset,int limit,int userId);
    List<Integer> queryAllInOne(int userId);
    int countBySale(int limit);
    int countByRegion(int limit,int userId);
    int countByRatio(int limit);
    int countBySupplier(int limit,int userId);
}
