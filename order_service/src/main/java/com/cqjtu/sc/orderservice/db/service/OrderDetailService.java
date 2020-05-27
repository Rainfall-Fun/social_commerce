package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.AllOrderDetailMapper;
import com.cqjtu.sc.orderservice.db.domain.AllOrderDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderDetailService {
    @Resource
    AllOrderDetailMapper mapper;

    public void addBatch(List<AllOrderDetail> list){
        mapper.batchInsertOrderDetail(list);
    }
}
