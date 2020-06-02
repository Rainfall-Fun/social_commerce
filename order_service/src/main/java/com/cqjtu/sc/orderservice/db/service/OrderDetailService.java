package com.cqjtu.sc.orderservice.db.service;

import com.cqjtu.sc.orderservice.db.dao.AllOrderDetailMapper;
import com.cqjtu.sc.orderservice.db.domain.AllOrder;
import com.cqjtu.sc.orderservice.db.domain.AllOrderDetail;
import com.cqjtu.sc.orderservice.db.domain.AllOrderDetailExample;
import com.cqjtu.sc.orderservice.db.domain.UnpaidOrderInfo;
import com.cqjtu.sc.orderservice.util.CountGoodsStatus;
import com.cqjtu.sc.orderservice.vo.OrderDetailVo;
import com.cqjtu.sc.orderservice.vo.OrderVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderDetailService {
    @Resource
    AllOrderDetailMapper mapper;
    @Autowired
    OrderService orderService;

    public List<AllOrderDetail> queryByUserIdAndStatus(Integer userId,List<Integer> status,Integer page,Integer limit){
        List<Integer> integers = orderService.queryOrderIdsByUserId(userId);
        AllOrderDetailExample example=new AllOrderDetailExample();
        AllOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdIn(integers);
        if (status!=null){
            criteria.andGoodsstatusIn(status);
        }
        PageHelper.startPage(page,limit);
        AllOrderDetail.Column[] c={AllOrderDetail.Column.orderDetailId};
        List<AllOrderDetail> orderDetailList = mapper.selectByExampleSelective(example,c);
        return orderDetailList;
    }
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


    public List<OrderVo> selectBySpecifiedKey(Integer userId,List<Integer> goodsStatus,String orderSn){
        return mapper.selectOrder(userId,goodsStatus,orderSn);
    }

    public OrderDetailVo selectOrderDetail(Integer userId){
        return mapper.selectOrderDetail(userId);
    }
    public void update(AllOrderDetail orderDetail){
        mapper.updateByPrimaryKeySelective(orderDetail);
    }

    public HashMap<Integer,Integer> countForGoodsStatus(Integer userId){
        List<CountGoodsStatus> list = mapper.countForGoodsStatus(userId);
        HashMap<Integer,Integer> map=new HashMap<>();
        for (CountGoodsStatus countGoodsStatus : list) {
            map.put(countGoodsStatus.getGoodsStatus(),countGoodsStatus.getSum());
        }
        return map;

    }

    public List<UnpaidOrderInfo> selectUnpaidOrderInfo(Integer userId,List<Integer> orderIds){
        return mapper.selectUnpaidOrderInfo(userId,orderIds);
    }

    public Page<UnpaidOrderInfo> selectOrderInfo(Integer userId,List<Integer> status,Integer page,Integer limit){
        int i = mapper.countByUserIdAndStatus(userId, status,limit);
        Page<UnpaidOrderInfo> unpaidOrderInfos = mapper.selectByUserIdAndStatus(userId, status, (page - 1) * limit, limit);
        unpaidOrderInfos.setPages(i);
        unpaidOrderInfos.setPageSize(limit);
        unpaidOrderInfos.setPageNum(page);
        return unpaidOrderInfos;
    }
}
