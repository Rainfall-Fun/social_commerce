package com.cqjtu.sc.orderservice.task;

import com.cqjtu.sc.orderservice.db.domain.AllOrder;
import com.cqjtu.sc.orderservice.db.domain.AllOrderDetail;
import com.cqjtu.sc.orderservice.db.service.OrderDetailService;
import com.cqjtu.sc.orderservice.db.service.OrderService;
import com.cqjtu.sc.orderservice.db.service.ProductService;
import com.cqjtu.sc.orderservice.util.BeanUtil;
import com.cqjtu.sc.orderservice.util.OrderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.time.LocalDateTime;
import java.util.List;

public class OrderUnpaidTask extends Task {
    private final Log logger = LogFactory.getLog(OrderUnpaidTask.class);
    private int orderId = -1;

    public OrderUnpaidTask(Integer orderId, long delayInMilliseconds) {
        super("OrderUnpaidTask-" + orderId, delayInMilliseconds);
        this.orderId = orderId;
    }

    public OrderUnpaidTask(Integer orderId) {
        super("OrderUnpaidTask-" + orderId, 1 * 60 * 1000);
        this.orderId = orderId;
    }

    @Override
    public void run() {
        logger.info("系统开始处理延时任务---订单超时未付款---" + this.orderId);

        OrderService orderService = BeanUtil.getBean(OrderService.class);
        OrderDetailService orderGoodsService = BeanUtil.getBean(OrderDetailService.class);
        ProductService productService = BeanUtil.getBean(ProductService.class);

        System.out.println("order id "+this.orderId);
        AllOrder order = orderService.getById(this.orderId);
        if (order == null) {
            return;
        }
        if (order.getOrderStatus() != OrderUtil.STATUS_CREATE) {
            return;
        }

        // 设置订单已取消状态
        order.setOrderStatus(OrderUtil.STATUS_AUTO_CANCEL);
        orderService.update(order);

        // 商品货品数量增加
        Integer orderId = order.getOrderId();
        List<AllOrderDetail> orderGoodsList = orderGoodsService.findByOrderId(orderId);
        for (AllOrderDetail orderGoods : orderGoodsList) {
            Integer productId = orderGoods.getId();
            Integer number = orderGoods.getQuantity();
            if (productService.addNumber(productId, number) == 0) {
                throw new RuntimeException("商品货品库存增加失败");
            }
        }
        orderGoodsService.updateStatusByOrderId(orderId,OrderUtil.STATUS_AUTO_CANCEL);
        System.out.println("系统结束处理延时任务---订单超时未付款---" + this.orderId);
        logger.info("系统结束处理延时任务---订单超时未付款---" + this.orderId);
    }
}
