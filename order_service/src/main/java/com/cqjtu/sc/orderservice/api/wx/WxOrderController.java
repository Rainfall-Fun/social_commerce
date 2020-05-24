package com.cqjtu.sc.orderservice.api.wx;

import com.cqjtu.sc.orderservice.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.orderservice.db.service.ProductService;
import com.cqjtu.sc.orderservice.dto.CheckDto;
import com.cqjtu.sc.orderservice.dto.PurchaseGoodsDto;
import com.cqjtu.sc.orderservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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

    @PostMapping("checkout")
    public Object checked(Integer userId, @RequestBody CheckDto body) {
        PurchaseGoodsDto[] purchaseGoods = body.getPurchaseGoods();
        List<Integer> productId=new ArrayList<>();
        Map<Integer,Integer> numberMap=new HashMap<>();
        for (PurchaseGoodsDto purchaseGood : purchaseGoods) {
            productId.add(purchaseGood.getProductId());
            numberMap.put(purchaseGood.getProductId(),purchaseGood.getNumber());
        }
        List<AllGoodsSpecifiAttValue> allGoodsSpecifiAttValues = productService.queryInList(productId);
        BigDecimal orderTotalPrice=new BigDecimal(0);
        for (AllGoodsSpecifiAttValue allGoodsSpecifiAttValue : allGoodsSpecifiAttValues) {
            orderTotalPrice=orderTotalPrice.add(allGoodsSpecifiAttValue.getPrice().multiply(new BigDecimal(numberMap.get(allGoodsSpecifiAttValue.getId()))));
        }
        Map<String, Object> data = new HashMap<>();
        data.put("addressId", 0);
        data.put("checkedAddress", 0);
        data.put("goodsTotalPrice", orderTotalPrice);
        data.put("freightPrice", 0);
        data.put("orderTotalPrice", orderTotalPrice);
        data.put("actualPrice", orderTotalPrice);
        data.put("checkedGoodsList",null);
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
    public Object submit(Integer userId, @RequestBody String body) {
        return ResponseUtil.ok();
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