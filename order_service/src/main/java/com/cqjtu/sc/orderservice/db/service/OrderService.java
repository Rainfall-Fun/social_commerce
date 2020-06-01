package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.AllOrderMapper;
import com.cqjtu.sc.orderservice.db.domain.AllOrder;
import com.cqjtu.sc.orderservice.db.domain.AllOrderExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    public void update(AllOrder order){
        mapper.updateByPrimaryKeySelective(order);
    }

    public AllOrder getById(Integer orderId){
        return mapper.selectByPrimaryKey(orderId);
    }
    public List<AllOrder> queryUnpaid(Integer userId, Integer page, Integer limit,String sort, String order){
        AllOrderExample example=new AllOrderExample();
        AllOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserInfoIdEqualTo(userId).andPayTimeIsNull();
        example.setOrderByClause(sort+" "+order);
        PageHelper.startPage(page, limit);
        List<AllOrder> allOrders = mapper.selectByExample(example);
        return allOrders;
    }

    public List<Integer> queryOrderIdsByUserId(Integer userId){
        List<Integer> result=new ArrayList<>();
        AllOrderExample example=new AllOrderExample();
        AllOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserInfoIdEqualTo(userId);
        List<AllOrder> allOrders = mapper.selectByExample(example);
        for (AllOrder allOrder : allOrders) {
            result.add(allOrder.getOrderId());
        }
        return result;
    }
}
