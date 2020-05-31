package com.cqjtu.sc.orderservice.service;


import com.cqjtu.sc.orderservice.db.domain.AllOrderDetail;
import com.cqjtu.sc.orderservice.db.service.OrderDetailService;
import com.cqjtu.sc.orderservice.util.JacksonUtil;
import com.cqjtu.sc.orderservice.util.OrderUtil;
import com.cqjtu.sc.orderservice.util.ResponseUtil;
import com.cqjtu.sc.orderservice.vo.OrderDetailVo;
import com.cqjtu.sc.orderservice.vo.OrderVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service

public class AdminOrderService {

    @Autowired
    OrderDetailService orderDetailService;


    public Object list(Integer userId, String orderSn, List<Short> orderStatusArray,
                       Integer page, Integer limit, String sort, String order) {
//        List<LitemallOrder> orderList = orderService.querySelective(userId, orderSn, orderStatusArray, page, limit,
//                sort, order);
        List<OrderVo> orderVos = orderDetailService.selectBySpecifiedKey(userId, orderStatusArray, orderSn);
        for (OrderVo orderVo : orderVos) {
            System.out.println(orderVo.toString());
        }
        return ResponseUtil.okList(orderVos);
    }

    public Object detail(Integer id) {

        OrderDetailVo orderDetailVo = orderDetailService.selectOrderDetail(id);

        return ResponseUtil.ok(orderDetailVo);
    }

    /**
     * 订单退款
     * <p>
     * 1. 检测当前订单是否能够退款;
     * 2. 微信退款操作;
     * 3. 设置订单退款确认状态；
     * 4. 订单商品库存回库。
     * <p>
     * TODO
     * 虽然接入了微信退款API，但是从安全角度考虑，建议开发者删除这里微信退款代码，采用以下两步走步骤：
     * 1. 管理员登录微信官方支付平台点击退款操作进行退款
     * 2. 管理员登录litemall管理后台点击退款操作进行订单状态修改和商品库存回库
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @Transactional
    public Object refund(String body) {
//        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
//        String refundMoney = JacksonUtil.parseString(body, "refundMoney");

        return ResponseUtil.ok();
    }

    /**
     * 发货
     * 1. 检测当前订单是否能够发货
     * 2. 设置订单发货状态
     *
     * @param body 订单信息，{ orderId：xxx, shipSn: xxx, shipChannel: xxx }
     * @return 订单操作结果
     * 成功则 { errno: 0, errmsg: '成功' }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    public Object ship(String body) {
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        String shipSn = JacksonUtil.parseString(body, "shipSn");
        String shipChannel = JacksonUtil.parseString(body, "shipChannel");
        if (orderId == null || shipSn == null || shipChannel == null) {
            return ResponseUtil.badArgument();
        }

        AllOrderDetail orderDetail = orderDetailService.findById(orderId);

        orderDetail.setShipChannel(shipChannel);
        orderDetail.setShipSn(shipSn);
        orderDetail.setShipTime(LocalDateTime.now());
        orderDetail.setGoodsstatus(Integer.valueOf(OrderUtil.STATUS_SHIP));
        orderDetailService.update(orderDetail);
        return ResponseUtil.ok();
    }


    /**
     * 回复订单商品
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     * 成功则 { errno: 0, errmsg: '成功' }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    public Object reply(String body) {
//        Integer commentId = JacksonUtil.parseInteger(body, "commentId");
//        if (commentId == null || commentId == 0) {
//            return ResponseUtil.badArgument();
//        }
//

        return ResponseUtil.ok();
    }

}
