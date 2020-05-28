package com.cqjtu.sc.orderservice.api.wx;

import com.cqjtu.sc.orderservice.db.domain.*;
import com.cqjtu.sc.orderservice.db.service.*;
import com.cqjtu.sc.orderservice.dto.CheckDto;
import com.cqjtu.sc.orderservice.dto.PurchaseGoodsDto;
import com.cqjtu.sc.orderservice.dto.PurchaseOrderDto;
import com.cqjtu.sc.orderservice.util.ResponseUtil;
import com.cqjtu.sc.orderservice.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
    @Autowired
    CarrigeAddressService carrigeAddressService;
>>>>>>> 547cab64eb76ae209f57ad6843f97b3160e0f76a
=======
>>>>>>> parent of 547cab6... 下单已经实现
=======
>>>>>>> parent of 547cab6... 下单已经实现
=======
>>>>>>> parent of 547cab6... 下单已经实现

    @PostMapping("checkout")
    public Object checked(Integer userId, @RequestBody CheckDto body) {
        PurchaseGoodsDto[] purchaseGoods = body.getPurchaseGoods();
        List<Integer> productId=new ArrayList<>();
        List<Integer> goodsId=new ArrayList<>();
        Map<Integer,Integer> numberMap=new HashMap<>();
        List<GoodsVo> checkedGoodsList=new ArrayList<>();
        List<GoodsVo> uncheckedGoodsList=new ArrayList<>();
        for (PurchaseGoodsDto purchaseGood : purchaseGoods) {
            productId.add(purchaseGood.getProductId());
            numberMap.put(purchaseGood.getProductId(),purchaseGood.getNumber());
            goodsId.add(purchaseGood.getGoodsId());
        }
        List<AllGoodsSpecifiAttValue> allGoodsSpecifiAttValues = productService.queryInList(productId);
        BigDecimal orderTotalPrice=new BigDecimal(0);
        for (AllGoodsSpecifiAttValue allGoodsSpecifiAttValue : allGoodsSpecifiAttValues) {
            GoodsVo goodsVo=new GoodsVo();
            goodsVo.setGoodsId(allGoodsSpecifiAttValue.getGoodsId());
            goodsVo.setProductId(allGoodsSpecifiAttValue.getId());
            goodsVo.setSpecifications(allGoodsSpecifiAttValue.getSpecifications());
            goodsVo.setPrice(allGoodsSpecifiAttValue.getPrice());
            goodsVo.setNumber(numberMap.get(allGoodsSpecifiAttValue.getId()));
            if (numberMap.get(allGoodsSpecifiAttValue.getId())>allGoodsSpecifiAttValue.getNumber()){//库存不足
                uncheckedGoodsList.add(goodsVo);
                continue;
            }
            else {
                orderTotalPrice=orderTotalPrice.add(allGoodsSpecifiAttValue.getPrice().multiply(new BigDecimal(numberMap.get(allGoodsSpecifiAttValue.getId()))));
                checkedGoodsList.add(goodsVo);
            }
        }

        List<BriefGoods> briefGoods = briefGoodsService.getBriefGoods(goodsId);

        for (GoodsVo goodsVo : checkedGoodsList) {
            for (BriefGoods briefGood : briefGoods) {
                if (briefGood.getId()==goodsVo.getGoodsId()){
                    goodsVo.setGoodsName(briefGood.getName());
                    goodsVo.setGoodsPic("http://localhost:8777/"+briefGood.getPicUrl());
                }
            }
        }

        for (GoodsVo goodsVo : uncheckedGoodsList) {
            for (BriefGoods briefGood : briefGoods) {
                if (briefGood.getId()==goodsVo.getGoodsId()){
                    goodsVo.setGoodsName(briefGood.getName());
                    goodsVo.setGoodsPic("http://localhost:8777/"+briefGood.getPicUrl());
                }
            }
        }

        //此地址后面需要从数据库中获取
        AllCarrigeAddress address=new AllCarrigeAddress();
        address.setAddressId(1);
        address.setConsignee("彭椿悦");
        address.setTel("18888888888");
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        address.setAdress("重庆交通大学");
=======
        address.setAddress("重庆交通大学");
>>>>>>> 547cab64eb76ae209f57ad6843f97b3160e0f76a
=======
        address.setAdress("重庆交通大学");
>>>>>>> parent of 547cab6... 下单已经实现
=======
        address.setAdress("重庆交通大学");
>>>>>>> parent of 547cab6... 下单已经实现
=======
        address.setAdress("重庆交通大学");
>>>>>>> parent of 547cab6... 下单已经实现
        Map<String, Object> data = new HashMap<>();
        data.put("addressId", 0);
        data.put("checkedAddress", address);
        data.put("goodsTotalPrice", orderTotalPrice);
        data.put("freightPrice", 0);
        data.put("orderTotalPrice", orderTotalPrice);
        data.put("actualPrice", orderTotalPrice);
        data.put("checkedGoodsList",checkedGoodsList);
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
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.ok();
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
        return ResponseUtil.ok();
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
        List<GoodsVo> uncheckedGoods=new ArrayList<>();
        //检查未通过的商品
        List<GoodsVo> checkedGoods=new ArrayList<>();
        //检查通过的商品id
        List<Integer> productIds=new ArrayList<>();

        //检查库存是否充足，充足并在库存表中减少商品数量
        for (GoodsVo purchaseProduct : purchaseProducts) {
            boolean b = productService.reduceNumber(purchaseProduct.getProductId(), purchaseProduct.getNumber());
            if (b==false){
                uncheckedGoods.add(purchaseProduct);
                continue;
            }
            checkedGoods.add(purchaseProduct);
            productIds.add(purchaseProduct.getProductId());
        }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        //保存商品数量的map
        Map<Integer, Integer> productNumberMap = new HashMap<>();
=======
>>>>>>> 547cab64eb76ae209f57ad6843f97b3160e0f76a
=======
        //保存商品数量的map
        Map<Integer, Integer> productNumberMap = new HashMap<>();
>>>>>>> parent of 547cab6... 下单已经实现
=======
        //保存商品数量的map
        Map<Integer, Integer> productNumberMap = new HashMap<>();
>>>>>>> parent of 547cab6... 下单已经实现
=======
        //保存商品数量的map
        Map<Integer, Integer> productNumberMap = new HashMap<>();
>>>>>>> parent of 547cab6... 下单已经实现
        //保存商品价格的map
        Map<Integer,BigDecimal> productPriceMap= new HashMap<>();
        List<AllGoodsSpecifiAttValue> allGoodsSpecifiAttValues = productService.queryInList(productIds);
        for (AllGoodsSpecifiAttValue allGoodsSpecifiAttValue : allGoodsSpecifiAttValues) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            productNumberMap.put(allGoodsSpecifiAttValue.getId(),allGoodsSpecifiAttValue.getNumber());
=======
>>>>>>> 547cab64eb76ae209f57ad6843f97b3160e0f76a
=======
            productNumberMap.put(allGoodsSpecifiAttValue.getId(),allGoodsSpecifiAttValue.getNumber());
>>>>>>> parent of 547cab6... 下单已经实现
=======
            productNumberMap.put(allGoodsSpecifiAttValue.getId(),allGoodsSpecifiAttValue.getNumber());
>>>>>>> parent of 547cab6... 下单已经实现
=======
            productNumberMap.put(allGoodsSpecifiAttValue.getId(),allGoodsSpecifiAttValue.getNumber());
>>>>>>> parent of 547cab6... 下单已经实现
            productPriceMap.put(allGoodsSpecifiAttValue.getId(),allGoodsSpecifiAttValue.getPrice());
        }

        //计算订单总价
        BigDecimal totalOrderPrice=BigDecimal.ZERO;
        for (GoodsVo checkedGood : checkedGoods) {
            Integer productId=checkedGood.getProductId();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            totalOrderPrice=totalOrderPrice.add(productPriceMap.get(productId).multiply(BigDecimal.valueOf(productNumberMap.get(productId))));
        }
        //将订单信息写入订单表
        AllOrder order=new AllOrder();
        order.setAmount(totalOrderPrice);
        order.setAddressStr("地址");
=======
            totalOrderPrice=totalOrderPrice.add(productPriceMap.get(productId).multiply(BigDecimal.valueOf(checkedGood.getNumber())));
=======
            totalOrderPrice=totalOrderPrice.add(productPriceMap.get(productId).multiply(BigDecimal.valueOf(productNumberMap.get(productId))));
>>>>>>> parent of 547cab6... 下单已经实现
=======
            totalOrderPrice=totalOrderPrice.add(productPriceMap.get(productId).multiply(BigDecimal.valueOf(productNumberMap.get(productId))));
>>>>>>> parent of 547cab6... 下单已经实现
=======
            totalOrderPrice=totalOrderPrice.add(productPriceMap.get(productId).multiply(BigDecimal.valueOf(productNumberMap.get(productId))));
>>>>>>> parent of 547cab6... 下单已经实现
        }
        //将订单信息写入订单表
        AllOrder order=new AllOrder();
        order.setAmount(totalOrderPrice);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        order.setAddressStr(addressString);
>>>>>>> 547cab64eb76ae209f57ad6843f97b3160e0f76a
=======
        order.setAddressStr("地址");
>>>>>>> parent of 547cab6... 下单已经实现
=======
        order.setAddressStr("地址");
>>>>>>> parent of 547cab6... 下单已经实现
=======
        order.setAddressStr("地址");
>>>>>>> parent of 547cab6... 下单已经实现
        order.setUserInfoId(userId);
        order.setGenTime(LocalTime.now());
        int orderId = orderService.add(order);
        //异步为订单生成订单号


        //将商品信息写入订单明细
        List<AllOrderDetail> orderDetailList=new ArrayList<>();
        for (GoodsVo checkedGood : checkedGoods) {
            AllOrderDetail allOrderDetail=new AllOrderDetail();
            allOrderDetail.setGoodsId(checkedGood.getGoodsId());
            allOrderDetail.setSpecifiValueId(checkedGood.getProductId());
            allOrderDetail.setQuantity(checkedGood.getNumber());
            allOrderDetail.setGoodsstatus(101);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
            allOrderDetail.setOrderId(orderId);
>>>>>>> 547cab64eb76ae209f57ad6843f97b3160e0f76a
=======
>>>>>>> parent of 547cab6... 下单已经实现
=======
>>>>>>> parent of 547cab6... 下单已经实现
=======
>>>>>>> parent of 547cab6... 下单已经实现
            orderDetailList.add(allOrderDetail);
        }
        orderDetailService.addBatch(orderDetailList);
        //异步为订单明细生成订单明细号

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> 547cab64eb76ae209f57ad6843f97b3160e0f76a
=======

>>>>>>> parent of 547cab6... 下单已经实现
=======

>>>>>>> parent of 547cab6... 下单已经实现
=======

>>>>>>> parent of 547cab6... 下单已经实现
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
    public Object prepay(Integer userId, @RequestBody String body, HttpServletRequest request) {
        return ResponseUtil.ok();
    }

    /**
     * 微信H5支付
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
     *  TODO
     *  注意，这里pay-notify是示例地址，建议开发者应该设立一个隐蔽的回调地址
     *
     * @param request 请求内容
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
     * @param goodsId 商品ID
     * @return 待评价订单商品信息
     */
    @GetMapping("goods")
    public Object goods(Integer userId,
                        @NotNull Integer orderId,
                        @NotNull Integer goodsId) {
        return ResponseUtil.ok();
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
        return ResponseUtil.ok();
    }

}