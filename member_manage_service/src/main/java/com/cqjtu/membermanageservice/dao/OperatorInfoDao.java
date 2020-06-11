package com.cqjtu.membermanageservice.dao;

import com.cqjtu.membermanageservice.db.AllOperatorInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Mapper
//@Repository
public interface OperatorInfoDao {
    public AllOperatorInfo SearchOperatorInfo(Integer Operator_id);

}
