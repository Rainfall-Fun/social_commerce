package com.cqjtu.sc.orderservice.api.wx;

import com.cqjtu.sc.orderservice.db.domain.*;
import com.cqjtu.sc.orderservice.db.service.*;
import com.cqjtu.sc.orderservice.dto.CheckDto;
import com.cqjtu.sc.orderservice.dto.PurchaseGoodsDto;
import com.cqjtu.sc.orderservice.dto.PurchaseOrderDto;
import com.cqjtu.sc.orderservice.util.*;
import com.cqjtu.sc.orderservice.vo.CommentGoodsVo;
import com.cqjtu.sc.orderservice.vo.GoodsVo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/order")
@Validated
public class WxOrderController {
    @Autowired
    ProductService productService;
    @Autowired
    GoodsSpecificationService goodsSpecificationService;
    @Autowired
    BriefGoodsService briefGoodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    CarrigeAddressService carrigeAddressService;

    @PostMapping("checkout")
    public Object checked(Integer userId, @RequestBody CheckDto body) {
        PurchaseGoodsDto[] purchaseGoods = body.getPurchaseGoods();
        List<Integer> productId = new ArrayList<>();
        List<Integer> goodsId = new ArrayList<>();
        Map<Integer, Integer> numberMap = new HashMap<>();
        List<GoodsVo> checkedGoodsList = new ArrayList<>();
        List<GoodsVo> uncheckedGoodsList = new ArrayList<>();
        for (PurchaseGoodsDto purchaseGood : purchaseGoods) {
            productId.add(purchaseGood.getProductId());
            numberMap.put(purchaseGood.getProductId(), purchaseGood.getNumber());
            goodsId.add(purchaseGood.getGoodsId());
        }
        List<AllGoodsSpecifiAttValue> allGoodsSpecifiAttValues = productService.queryInList(productId);
        BigDecimal orderTotalPrice = new BigDecimal(0);
        for (AllGoodsSpecifiAttValue allGoodsSpecifiAttValue : allGoodsSpecifiAttValues) {
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setGoodsId(allGoodsSpecifiAttValue.getGoodsId());
            goodsVo.setProductId(allGoodsSpecifiAttValue.getId());
            goodsVo.setSpecifications(allGoodsSpecifiAttValue.getSpecifications());
            goodsVo.setPrice(allGoodsSpecifiAttValue.getPrice());
            goodsVo.setNumber(numberMap.get(allGoodsSpecifiAttValue.getId()));
            if (numberMap.get(allGoodsSpecifiAttValue.getId()) > allGoodsSpecifiAttValue.getNumber()) {//库存不足
                uncheckedGoodsList.add(goodsVo);
                continue;
            } else {
                orderTotalPrice = orderTotalPrice.add(allGoodsSpecifiAttValue.getPrice().multiply(new BigDecimal(numberMap.get(allGoodsSpecifiAttValue.getId()))));
                checkedGoodsList.add(goodsVo);
            }
        }

        List<BriefGoods> briefGoods = briefGoodsService.getBriefGoods(goodsId);

        for (GoodsVo goodsVo : checkedGoodsList) {
            for (BriefGoods briefGood : briefGoods) {
                if (briefGood.getId() == goodsVo.getGoodsId()) {
                    goodsVo.setGoodsName(briefGood.getName());
                    goodsVo.setGoodsPic("http://localhost:8777/" + briefGood.getPicUrl());
                }
            }
        }

        for (GoodsVo goodsVo : uncheckedGoodsList) {
            for (BriefGoods briefGood : briefGoods) {
                if (briefGood.getId() == goodsVo.getGoodsId()) {
                    goodsVo.setGoodsName(briefGood.getName());
                    goodsVo.setGoodsPic("http://localhost:8777/" + briefGood.getPicUrl());
                }
            }
        }

        //此地址后面需要从数据库中获取
        AllCarrigeAddress address = new AllCarrigeAddress();
        address.setAddressId(1);
        address.setConsignee("彭椿悦");
        address.setTel("18888888888");
        address.setAddress("重庆交通大学");
        Map<String, Object> data = new HashMap<>();
        data.put("addressId", 0);
        data.put("checkedAddress", address);
        data.put("goodsTotalPrice", orderTotalPrice);
        data.put("freightPrice", 0);
        data.put("orderTotalPrice", orderTotalPrice);
        data.put("actualPrice", orderTotalPrice);
        data.put("checkedGoodsList", checkedGoodsList);
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//        if (body == null) {
//            return ResponseUtil.badArgument();
//        }


        return ResponseUtil.ok(data);
    }


    @GetMapping("list")
    public Object list(Integer userId,
                       @RequestParam(defaultValue = "0") Integer showType,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "order_id") String sort,
                       @RequestParam(defaultValue = "desc") String order) {

        if (showType==0){
            List<AllOrder> allOrders = orderService.queryUnpaid(userId, page, limit, sort, order);
            List<Integer> orderIds=new ArrayList<>();
            for (AllOrder allOrder : allOrders) {
                orderIds.add(allOrder.getOrderId());
            }
            List<Map<String, Object>> orderVoList = new ArrayList<>();
            List<UnpaidOrderInfo> unpaidOrderInfos = orderDetailService.selectUnpaidOrderInfo(userId, orderIds);
            for (AllOrder allOrder : allOrders) {
                Map<String, Object> orderVo = new HashMap<>();
                orderVo.put("id", allOrder.getOrderId());
                orderVo.put("orderSn", allOrder.getOrderNumber());
                orderVo.put("actualPrice", allOrder.getAmount());
                orderVo.put("orderStatusText", OrderUtil.orderStatusText(OrderUtil.STATUS_CREATE));
                orderVo.put("handleOption", OrderUtil.build(OrderUtil.STATUS_CREATE));
                List<Map<String, Object>> orderGoodsVoList = new ArrayList<>();
                List<UnpaidOrderInfo> byId = getById(unpaidOrderInfos, allOrder.getOrderId());
                for (UnpaidOrderInfo orderGoods : byId) {
                    Map<String, Object> orderGoodsVo = new HashMap<>();
                    orderGoodsVo.put("id", orderGoods.getId());
                    orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
                    orderGoodsVo.put("number", orderGoods.getNumber());
                    orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
                    orderGoodsVo.put("specifications", orderGoods.getSpecifications());
//                    orderGoodsVo.put("price",orderGoods.getPrice());
                    orderGoodsVoList.add(orderGoodsVo);
                }
                orderVo.put("goodsList", orderGoodsVoList);
                orderVoList.add(orderVo);

            }
            return ResponseUtil.okList(orderVoList,allOrders);
        }else {
            List<Integer> orderStatus = OrderUtil.orderStatus(showType);
            List<Integer> status=new ArrayList<>();
            if (orderStatus==null){
                status.add(null);
            }else {
                for (Integer aShort : orderStatus) {
                    status.add(aShort);
                }
            }
            Page<UnpaidOrderInfo> unpaidOrderInfos = orderDetailService.selectOrderInfo(userId, orderStatus,page,limit);
            Page<Map<String, Object>> orderVoList = new Page<>();
            for (UnpaidOrderInfo unpaidOrderInfo : unpaidOrderInfos) {
                Map<String, Object> orderVo = new HashMap<>();
                orderVo.put("id", unpaidOrderInfo.getId());
                orderVo.put("orderSn", unpaidOrderInfo.getOrderSn());
                orderVo.put("actualPrice", unpaidOrderInfo.getActualPrice());
                orderVo.put("orderStatusText", OrderUtil.orderStatusText(unpaidOrderInfo.getStatus()));
                orderVo.put("handleOption", OrderUtil.build(unpaidOrderInfo.getStatus()));
                List<Map<String, Object>> orderGoodsVoList = new ArrayList<>();

                Map<String, Object> orderGoodsVo = new HashMap<>();
                orderGoodsVo.put("id", unpaidOrderInfo.getGoodsId());
                orderGoodsVo.put("goodsName", unpaidOrderInfo.getGoodsName());
                orderGoodsVo.put("number", unpaidOrderInfo.getNumber());
                orderGoodsVo.put("picUrl", unpaidOrderInfo.getPicUrl());
                orderGoodsVo.put("specifications", unpaidOrderInfo.getSpecifications());
//                    orderGoodsVo.put("price",orderGoods.getPrice());
                orderGoodsVoList.add(orderGoodsVo);
                orderVo.put("goodsList", orderGoodsVoList);
                orderVoList.add(orderVo);
            }
            orderVoList.setPages(unpaidOrderInfos.getPages());
            orderVoList.setPageNum(page);
            orderVoList.setPageSize(limit);
            return ResponseUtil.okList(orderVoList);
        }


    }

    List<UnpaidOrderInfo> getById(List<UnpaidOrderInfo> unpaidOrderInfos,Integer id){
        List<UnpaidOrderInfo> result=new ArrayList<>();
        for (UnpaidOrderInfo unpaidOrderInfo : unpaidOrderInfos) {
            if (unpaidOrderInfo.getId()==id)
                result.add(unpaidOrderInfo);
        }
        return result;
    }

    /**
     * 订单详情
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("detail")
    public Object detail(Integer userId, @NotNull Integer orderId) {
        AllOrder byId = orderService.getById(orderId);
        return ResponseUtil.ok(byId);
    }

    /**
     * 提交订单
     *
     * @param userId 用户ID
     * @param body   订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @PostMapping("submit")
    public Object submit(Integer userId, @RequestBody PurchaseOrderDto body) throws Exception {
        GoodsVo[] purchaseProducts = body.getPurchaseProducts();
        //检查通过的商品
        List<GoodsVo> uncheckedGoods = new ArrayList<>();
        //检查未通过的商品
        List<GoodsVo> checkedGoods = new ArrayList<>();
        //检查通过的商品id
        List<Integer> productIds = new ArrayList<>();

        //检查库存是否充足，充足并在库存表中减少商品数量
        for (GoodsVo purchaseProduct : purchaseProducts) {
            boolean b = productService.reduceNumber(purchaseProduct.getProductId(), purchaseProduct.getNumber());
            if (b == false) {
                uncheckedGoods.add(purchaseProduct);
                continue;
            }
            checkedGoods.add(purchaseProduct);
            productIds.add(purchaseProduct.getProductId());
        }


        //保存商品价格的map
        Map<Integer, BigDecimal> productPriceMap = new HashMap<>();
        List<AllGoodsSpecifiAttValue> allGoodsSpecifiAttValues = productService.queryInList(productIds);
        for (AllGoodsSpecifiAttValue allGoodsSpecifiAttValue : allGoodsSpecifiAttValues) {
            productPriceMap.put(allGoodsSpecifiAttValue.getId(), allGoodsSpecifiAttValue.getPrice());
        }

        //计算订单总价
        BigDecimal totalOrderPrice = BigDecimal.ZERO;
        for (GoodsVo checkedGood : checkedGoods) {
            Integer productId = checkedGood.getProductId();
            totalOrderPrice = totalOrderPrice.add(productPriceMap.get(productId).multiply(BigDecimal.valueOf(checkedGood.getNumber())));
        }

        //收货地址
        Integer addressId = body.getAddressId();
        addressId = 1;//后面需要删除
        String addressString = carrigeAddressService.addressString(addressId);
        //将订单信息写入订单表
        AllOrder order = new AllOrder();
        order.setAmount(totalOrderPrice);
        order.setAddressStr(addressString);
        order.setUserInfoId(userId);
        order.setGenTime(LocalDateTime.now());
        int orderId = orderService.add(order);

        order.setOrderId(orderId);
        String orderSn = System.currentTimeMillis() + "" + orderId;
        order.setOrderNumber(orderSn);
        orderService.update(order);


        //将商品信息写入订单明细
        List<AllOrderDetail> orderDetailList = new ArrayList<>();
        for (int i = 0; i < checkedGoods.size(); i++) {
            GoodsVo checkedGood = checkedGoods.get(i);
            AllOrderDetail allOrderDetail = new AllOrderDetail();
            allOrderDetail.setGoodsId(checkedGood.getGoodsId());
            allOrderDetail.setId(checkedGood.getProductId());
            allOrderDetail.setQuantity(checkedGood.getNumber());
            allOrderDetail.setGoodsstatus(101);
            allOrderDetail.setOrderId(orderId);
            allOrderDetail.setAmount(BigDecimal.valueOf(checkedGood.getNumber()).multiply(productPriceMap.get(checkedGood.getProductId())));
            allOrderDetail.setOrderDetailNumber(orderSn + i);
            orderDetailList.add(allOrderDetail);
        }
        orderDetailService.addBatch(orderDetailList);


        return ResponseUtil.ok(orderId);
    }

    /**
     * 取消订单
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @PostMapping("cancel")
    public Object cancel(Integer userId, @RequestBody String body) {
        return ResponseUtil.ok();
    }

    /**
     * 付款订单的预支付会话标识
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 支付订单ID
     */
    @PostMapping("prepay")
    public Object prepay(Integer userId, @RequestBody String body) {
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        AllOrder byId = orderService.getById(orderId);
        byId.setPayTime(LocalDateTime.now());
        orderService.update(byId);
        List<AllOrderDetail> byOrderId = orderDetailService.findByOrderId(orderId);
        for (AllOrderDetail allOrderDetail : byOrderId) {
            allOrderDetail.setGoodsstatus(Integer.valueOf(OrderUtil.STATUS_PAY));
            orderDetailService.update(allOrderDetail);
        }
        return ResponseUtil.ok();
    }

    /**
     * 微信H5支付
     *
     * @param userId
     * @param body
     * @param request
     * @return
     */
    @PostMapping("h5pay")
    public Object h5pay(Integer userId, @RequestBody String body, HttpServletRequest request) {
        return ResponseUtil.ok();
    }

    /**
     * 微信付款成功或失败回调接口
     * <p>
     * TODO
     * 注意，这里pay-notify是示例地址，建议开发者应该设立一个隐蔽的回调地址
     *
     * @param request  请求内容
     * @param response 响应内容
     * @return 操作结果
     */
    @PostMapping("pay-notify")
    public Object payNotify(HttpServletRequest request, HttpServletResponse response) {
        return ResponseUtil.ok();
    }

    /**
     * 订单申请退款
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @PostMapping("refund")
    public Object refund(Integer userId, @RequestBody String body) {
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        AllOrderDetail orderDetail = orderDetailService.findById(orderId);
        OrderHandleOption handleOption = OrderUtil.build(orderDetail.getGoodsstatus());
        if (!handleOption.isRefund()) {
            return ResponseUtil.fail(WxResponseCode.ORDER_INVALID_OPERATION, "订单不能取消");
        }
        orderDetail.setGoodsstatus(OrderUtil.STATUS_REFUND);
        orderDetailService.update(orderDetail);
        return ResponseUtil.ok();
    }

    /**
     * 确认收货
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("confirm")
    public Object confirm(Integer userId, @RequestBody String body) {
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        AllOrderDetail byId = orderDetailService.findById(orderId);
        byId.setGoodsstatus(Integer.valueOf(OrderUtil.STATUS_CONFIRM));
        orderDetailService.update(byId);
        return ResponseUtil.ok();
    }

    /**
     * 删除订单
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("delete")
    public Object delete(Integer userId, @RequestBody String body) {
        return ResponseUtil.ok();
    }

    /**
     * 待评价订单商品信息
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @return 待评价订单商品信息
     */
    @GetMapping("goods")
    public Object goods(Integer userId,
                        @NotNull Integer orderId) {
        CommentGoodsVo commentGoodsInfo = orderDetailService.getCommentGoodsInfo(userId, orderId);
        return ResponseUtil.ok(commentGoodsInfo);
    }

    /**
     * 评价订单商品
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("comment")
    public Object comment(Integer userId, @RequestBody String body) {
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        Integer rate = JacksonUtil.parseInteger(body,"rate");
        String message = JacksonUtil.parseString(body, "message");
        AllOrderDetail byId = orderDetailService.findById(orderId);
        byId.setRatio(rate);
        byId.setGoodsstatus(OrderUtil.STATUS_COMMENTED);
        orderDetailService.update(byId);
        return ResponseUtil.ok();
    }

    @GetMapping("index")
    public Object index(Integer userId) {

        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;

        HashMap<Integer, Integer> statusSum = orderDetailService.countForGoodsStatus(userId);
        unpaid = statusSum.get(OrderUtil.STATUS_CREATE)==null?0:statusSum.get(OrderUtil.STATUS_CREATE);
        unship = statusSum.get(OrderUtil.STATUS_PAY)==null?0:statusSum.get(OrderUtil.STATUS_PAY);
        unrecv = statusSum.get(OrderUtil.STATUS_SHIP)==null?0:statusSum.get(OrderUtil.STATUS_SHIP);
        int a1 = statusSum.get(OrderUtil.STATUS_CONFIRM) == null ? 0 : statusSum.get(OrderUtil.STATUS_CONFIRM);
        int a2 = statusSum.get(OrderUtil.STATUS_AUTO_CONFIRM) == null ? 0 : statusSum.get(OrderUtil.STATUS_AUTO_CONFIRM);
        uncomment = a1 + a2;
        Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
        return ResponseUtil.ok(orderInfo);
    }

}