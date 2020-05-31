package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.AllOrderDetailMapper;
import com.cqjtu.sc.orderservice.db.domain.AllOrder;
import com.cqjtu.sc.orderservice.db.domain.AllOrderDetail;
import com.cqjtu.sc.orderservice.db.domain.AllOrderDetailExample;
import com.cqjtu.sc.orderservice.util.CountGoodsStatus;
import com.cqjtu.sc.orderservice.vo.OrderDetailVo;
import com.cqjtu.sc.orderservice.vo.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderDetailService {
    @Resource
    AllOrderDetailMapper mapper;

    public AllOrderDetail findById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<AllOrderDetail> findByOrderId(Integer orderId){
        AllOrderDetailExample example=new AllOrderDetailExample();
        AllOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        return mapper.selectByExample(example);
    }
    public void addBatch(List<AllOrderDetail> list){
        mapper.batchInsertOrderDetail(list);
    }


    public List<OrderVo> selectBySpecifiedKey(Integer userId,List<Short> goodsStatus,String orderSn){
        return mapper.selectOrder(userId,goodsStatus,orderSn);
    }

    public OrderDetailVo selectOrderDetail(Integer userId){
        return mapper.selectOrderDetail(userId);
    }
    public void update(AllOrderDetail orderDetail){
        mapper.updateByPrimaryKeySelective(orderDetail);
    }

    public HashMap<Short,Integer> countForGoodsStatus(Integer userId){
        List<CountGoodsStatus> list = mapper.countForGoodsStatus(userId);
        HashMap<Short,Integer> map=new HashMap<>();
        for (CountGoodsStatus countGoodsStatus : list) {
            map.put(countGoodsStatus.getGoodsStatus(),countGoodsStatus.getSum());
        }
        return map;

    }
}
