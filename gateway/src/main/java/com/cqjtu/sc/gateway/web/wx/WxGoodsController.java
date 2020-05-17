package com.cqjtu.sc.gateway.web.wx;

import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.web.service.GoodsService;
import com.cqjtu.sc.gateway.web.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx/goods/")
public class WxGoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    RecommendService recommendService;
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
    public Object recommendList(Integer recommendType,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        Object recommendGoodsList = recommendService.getRecommendGoodsList(recommendType, page, limit);
        return recommendGoodsList;
    }
    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }


    public static Object unlogin() {
        return fail(501, "请登录");
    }

}
