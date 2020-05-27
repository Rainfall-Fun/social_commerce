package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.AllOrderMapper;
import com.cqjtu.sc.orderservice.db.domain.AllOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {
    @Resource
    AllOrderMapper mapper;

    /**
     * 添加订单
     * @param order
     * @return
     */
    public int add(AllOrder order){
        mapper.insert(order);
        return order.getOrderId();
    }
}
