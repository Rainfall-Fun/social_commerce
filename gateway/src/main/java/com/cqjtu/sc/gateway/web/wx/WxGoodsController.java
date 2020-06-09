package com.cqjtu.sc.gateway.web.wx;

import com.cqjtu.sc.gateway.annotation.LoginUser;
import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.web.service.GoodsService;
import com.cqjtu.sc.gateway.web.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx/goods/")
public class WxGoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    RecommendService recommendService;

    @GetMapping("detail")
    public Object detail(@LoginUser Integer userId, @NotNull Integer id) {
        Object detail = goodsService.detail(3, id);
        return detail;
    }
    @RequestMapping("list")
    public Object test(Integer categoryId,
                       Integer brandId,
                       String keyword,
                       Boolean isNew,
                       Boolean isHot,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order) {
        Object data = goodsService.list(categoryId, brandId, keyword, isNew, isHot, page, limit, sort, order);
        return ResponseUtil.ok(data);
    }

    @RequestMapping("recommendList")
    public Object recommendList(@LoginUser Integer userId, Integer recommendType,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Object recommendGoodsList = recommendService.getRecommendGoodsList(recommendType,userId, page, limit);
        return recommendGoodsList;
    }

    @RequestMapping("supplierRecommend")
    public Object supplierRecommend(@LoginUser Integer userId,@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit){
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        return recommendService.getSupplierOtherGoods(userId,page,limit);
    }

}
