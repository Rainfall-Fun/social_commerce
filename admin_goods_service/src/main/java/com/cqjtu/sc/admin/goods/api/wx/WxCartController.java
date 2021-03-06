package com.cqjtu.sc.admin.goods.api.wx;

import com.cqjtu.sc.admin.goods.db.domain.AllCart;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.admin.goods.db.service.BriefGoodsService;
import com.cqjtu.sc.admin.goods.db.service.CartService;
import com.cqjtu.sc.admin.goods.db.service.GoodsSpecifiAttValService;
import com.cqjtu.sc.admin.goods.dto.BriefGoods;
import com.cqjtu.sc.admin.goods.util.ResponseUtil;
import com.cqjtu.sc.admin.goods.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户购物车服务
 */
@RestController
@RequestMapping("/wx/cart")
@Validated
public class WxCartController {


    @Autowired
    CartService cartService;
    @Autowired
    GoodsSpecifiAttValService goodsSpecifiAttValService;
    @Autowired
    BriefGoodsService briefGoodsService;
    /**
     * 用户购物车信息
     *
     * @param userInfoId 用户ID
     * @return 用户购物车信息
     */
    @GetMapping("index")
    public Object index(Integer userInfoId) {
        List<CartVo> cartVos=new ArrayList<>();
        List<AllCart> allCarts = cartService.queryByUserInfoId(userInfoId);
        List<Integer> goodsIds=new ArrayList<>();
        //将库存充足的显示，最好是把库存不足的也放在前端，让用户删除
        System.out.println(allCarts.size()+"aaaa");
        for (AllCart allCart : allCarts) {
            AllGoodsSpecifiAttValue allGoodsSpecifiAttValue = goodsSpecifiAttValService.queryById(allCart.getId());
            if (allGoodsSpecifiAttValue.getNumber()>=allCart.getNumber()){
                CartVo cartVo=new CartVo();
                cartVo.setCartId(allCart.getCartId());
                cartVo.setGoodsId(allCart.getGoodsId());
                cartVo.setNumber(allCart.getNumber());
                cartVo.setPrice(allGoodsSpecifiAttValue.getPrice());
                cartVo.setProductId(allGoodsSpecifiAttValue.getId());
                cartVo.setSpecifications(allGoodsSpecifiAttValue.getSpecifications());
                goodsIds.add(allCart.getGoodsId());
                cartVos.add(cartVo);
            }
        }
        if (goodsIds.size()==0){
            Map<String, Object> cartTotal = new HashMap<>();
//        cartTotal.put("goodsCount", goodsCount);
//        cartTotal.put("goodsAmount", goodsAmount);
//        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
//        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);
            Map<String, Object> result = new HashMap<>();
            result.put("cartList", cartVos);
            result.put("cartTotal", cartTotal);
            return ResponseUtil.ok(result);
        }
        List<BriefGoods> briefGoods = briefGoodsService.getBriefGoods(goodsIds);
        Map<Integer,String> goodsNameMap=new HashMap<>();
        Map<Integer,String> imgMap=new HashMap<>();
        for (BriefGoods briefGood : briefGoods) {
            goodsNameMap.put(briefGood.getId(),briefGood.getName());
            imgMap.put(briefGood.getId(),briefGood.getPicUrl());
        }
        for (CartVo cartVo : cartVos) {
            cartVo.setGoodsName(goodsNameMap.get(cartVo.getGoodsId()));
            cartVo.setPicUrl("http://112.124.20.144:8080/"+imgMap.get(cartVo.getGoodsId()));
            cartVo.setChecked(true);
        }

        Map<String, Object> cartTotal = new HashMap<>();
//        cartTotal.put("goodsCount", goodsCount);
//        cartTotal.put("goodsAmount", goodsAmount);
//        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
//        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);
        Map<String, Object> result = new HashMap<>();
        result.put("cartList", cartVos);
        result.put("cartTotal", cartTotal);
        return ResponseUtil.ok(result);
    }

    /**
     * 加入商品到购物车
     * <p>
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param userInfoId 用户ID
     * @param cart   购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 加入购物车操作结果
     */
    @PostMapping("add")
    public Object add(Integer userInfoId, @RequestBody AllCart cart) {
        cart.setUserInfoId(userInfoId);
        //如果有此规格直接添加，否则在数据库中创建
        AllCart allCart = cartService.existByProductIdAndUserId(cart.getId(),userInfoId);
        if (allCart!=null){
            allCart.setNumber(allCart.getNumber()+cart.getNumber());
            cartService.update(allCart);
        }else
            cartService.add(cart);
        return goodscount(userInfoId);
    }



    /**
     * 修改购物车商品货品数量
     *
     * @param userInfoId 用户ID
     * @param cart   购物车商品信息， { id: xxx, goodsId: xxx, productId: xxx, number: xxx }
     * @return 修改结果
     */
    @PostMapping("update")
    public Object update(Integer userInfoId, @RequestBody String cart) {
        if (cart == null) {
            return ResponseUtil.badArgument();
        }

        return ResponseUtil.ok();
    }

//    /**
//     * 购物车商品货品勾选状态
//     * <p>
//     * 如果原来没有勾选，则设置勾选状态；如果商品已经勾选，则设置非勾选状态。
//     *
//     * @param userId 用户ID
//     * @param body   购物车商品信息， { productIds: xxx, isChecked: 1/0 }
//     * @return 购物车信息
//     */
//    @PostMapping("checked")
//    public Object checked(@LoginUser Integer userId, @RequestBody String body) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//        if (body == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        List<Integer> productIds = JacksonUtil.parseIntegerList(body, "productIds");
//        if (productIds == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        Integer checkValue = JacksonUtil.parseInteger(body, "isChecked");
//        if (checkValue == null) {
//            return ResponseUtil.badArgument();
//        }
//        Boolean isChecked = (checkValue == 1);
//
//        cartService.updateCheck(userId, productIds, isChecked);
//        return index(userId);
//    }

    /**
     * 购物车商品删除
     *
     * @param userInfoId 用户ID
     * @param body   购物车商品信息， { productIds: xxx }
     * @return 购物车信息
     * 成功则
     * {
     * errno: 0,
     * errmsg: '成功',
     * data: xxx
     * }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping("delete")
    public Object delete(Integer userInfoId, @RequestBody String body) {
        return index(userInfoId);
    }

    /**
     * 购物车商品货品数量
     * <p>
     * 如果用户没有登录，则返回空数据。
     *
     * @param userInfoId 用户ID
     * @return 购物车商品货品数量
     */
    @GetMapping("goodscount")
    public Object goodscount(Integer userInfoId) {
        long goodsCount = cartService.countByUserInfoId(userInfoId);
        return ResponseUtil.ok(goodsCount);
    }
//
//    /**
//     * 购物车下单
//     *
//     * @param userId    用户ID
//     * @param cartId    购物车商品ID：
//     *                  如果购物车商品ID是空，则下单当前用户所有购物车商品；
//     *                  如果购物车商品ID非空，则只下单当前购物车商品。
//     * @param addressId 收货地址ID：
//     *                  如果收货地址ID是空，则查询当前用户的默认地址。
//     * @param couponId  优惠券ID：
//     *                  如果优惠券ID是空，则自动选择合适的优惠券。
//     * @return 购物车操作结果
//     */
//    @GetMapping("checkout")
//    public Object checkout(@LoginUser Integer userId, Integer cartId, Integer addressId, Integer couponId, Integer userCouponId, Integer grouponRulesId) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//
//        // 收货地址
//        LitemallAddress checkedAddress = null;
//        if (addressId == null || addressId.equals(0)) {
//            checkedAddress = addressService.findDefault(userId);
//            // 如果仍然没有地址，则是没有收货地址
//            // 返回一个空的地址id=0，这样前端则会提醒添加地址
//            if (checkedAddress == null) {
//                checkedAddress = new LitemallAddress();
//                checkedAddress.setId(0);
//                addressId = 0;
//            } else {
//                addressId = checkedAddress.getId();
//            }
//
//        } else {
//            checkedAddress = addressService.query(userId, addressId);
//            // 如果null, 则报错
//            if (checkedAddress == null) {
//                return ResponseUtil.badArgumentValue();
//            }
//        }
//
//        // 团购优惠
//        BigDecimal grouponPrice = new BigDecimal(0.00);
//        LitemallGrouponRules grouponRules = grouponRulesService.findById(grouponRulesId);
//        if (grouponRules != null) {
//            grouponPrice = grouponRules.getDiscount();
//        }
//
//        // 商品价格
//        List<LitemallCart> checkedGoodsList = null;
//        if (cartId == null || cartId.equals(0)) {
//            checkedGoodsList = cartService.queryByUidAndChecked(userId);
//        } else {
//            LitemallCart cart = cartService.findById(cartId);
//            if (cart == null) {
//                return ResponseUtil.badArgumentValue();
//            }
//            checkedGoodsList = new ArrayList<>(1);
//            checkedGoodsList.add(cart);
//        }
//        BigDecimal checkedGoodsPrice = new BigDecimal(0.00);
//        for (LitemallCart cart : checkedGoodsList) {
//            //  只有当团购规格商品ID符合才进行团购优惠
//            if (grouponRules != null && grouponRules.getGoodsId().equals(cart.getGoodsId())) {
//                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().subtract(grouponPrice).multiply(new BigDecimal(cart.getNumber())));
//            } else {
//                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
//            }
//        }
//
//        // 计算优惠券可用情况
//        BigDecimal tmpCouponPrice = new BigDecimal(0.00);
//        Integer tmpCouponId = 0;
//        Integer tmpUserCouponId = 0;
//        int tmpCouponLength = 0;
//        List<LitemallCouponUser> couponUserList = couponUserService.queryAll(userId);
//        for(LitemallCouponUser couponUser : couponUserList){
//            tmpUserCouponId = couponUser.getId();
//            LitemallCoupon coupon = couponVerifyService.checkCoupon(userId, couponUser.getCouponId(), tmpUserCouponId, checkedGoodsPrice);
//            if(coupon == null){
//                continue;
//            }
//
//            tmpCouponLength++;
//            if(tmpCouponPrice.compareTo(coupon.getDiscount()) == -1){
//                tmpCouponPrice = coupon.getDiscount();
//                tmpCouponId = coupon.getId();
//            }
//        }
//        // 获取优惠券减免金额，优惠券可用数量
//        int availableCouponLength = tmpCouponLength;
//        BigDecimal couponPrice = new BigDecimal(0);
//        // 这里存在三种情况
//        // 1. 用户不想使用优惠券，则不处理
//        // 2. 用户想自动使用优惠券，则选择合适优惠券
//        // 3. 用户已选择优惠券，则测试优惠券是否合适
//        if (couponId == null || couponId.equals(-1)){
//            couponId = -1;
//            userCouponId = -1;
//        }
//        else if (couponId.equals(0)) {
//            couponPrice = tmpCouponPrice;
//            couponId = tmpCouponId;
//            userCouponId = tmpUserCouponId;
//        }
//        else {
//            LitemallCoupon coupon = couponVerifyService.checkCoupon(userId, couponId, userCouponId, checkedGoodsPrice);
//            // 用户选择的优惠券有问题，则选择合适优惠券，否则使用用户选择的优惠券
//            if(coupon == null){
//                couponPrice = tmpCouponPrice;
//                couponId = tmpCouponId;
//                userCouponId = tmpUserCouponId;
//            }
//            else {
//                couponPrice = coupon.getDiscount();
//            }
//        }
//
//        // 根据订单商品总价计算运费，满88则免运费，否则8元；
//        BigDecimal freightPrice = new BigDecimal(0.00);
//        if (checkedGoodsPrice.compareTo(SystemConfig.getFreightLimit()) < 0) {
//            freightPrice = SystemConfig.getFreight();
//        }
//
//        // 可以使用的其他钱，例如用户积分
//        BigDecimal integralPrice = new BigDecimal(0.00);
//
//        // 订单费用
//        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice).max(new BigDecimal(0.00));
//
//        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("addressId", addressId);
//        data.put("couponId", couponId);
//        data.put("userCouponId", userCouponId);
//        data.put("cartId", cartId);
//        data.put("grouponRulesId", grouponRulesId);
//        data.put("grouponPrice", grouponPrice);
//        data.put("checkedAddress", checkedAddress);
//        data.put("availableCouponLength", availableCouponLength);
//        data.put("goodsTotalPrice", checkedGoodsPrice);
//        data.put("freightPrice", freightPrice);
//        data.put("couponPrice", couponPrice);
//        data.put("orderTotalPrice", orderTotalPrice);
//        data.put("actualPrice", actualPrice);
//        data.put("checkedGoodsList", checkedGoodsList);
//        return ResponseUtil.ok(data);
//    }
}