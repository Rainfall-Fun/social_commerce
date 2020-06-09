package com.cqjtu.sc.gateway.web.wx;

import com.cqjtu.sc.gateway.annotation.LoginUser;
import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.web.service.GoodsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 首页服务
 */
@RestController
@RequestMapping("/wx/home")
@Validated
public class WxHomeController {


    @Autowired
    GoodsService goodsService;


    @GetMapping("/cache")
    public Object cache(@NotNull String key) {
        return null;
    }

    /**
     * 首页数据
     * @param userId 当用户已经登录时，非空。为登录状态为null
     * @return 首页数据
     */
    @GetMapping("/index")
    public Object index(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Object index = goodsService.index(userId);
        return index;
    }

//    private List<Map> getCategoryList() {
//        List<Map> categoryList = new ArrayList<>();
//        List<LitemallCategory> catL1List = categoryService.queryL1WithoutRecommend(0, SystemConfig.getCatlogListLimit());
//        for (LitemallCategory catL1 : catL1List) {
//            List<LitemallCategory> catL2List = categoryService.queryByPid(catL1.getId());
//            List<Integer> l2List = new ArrayList<>();
//            for (LitemallCategory catL2 : catL2List) {
//                l2List.add(catL2.getId());
//            }
//
//            List<LitemallGoods> categoryGoods;
//            if (l2List.size() == 0) {
//                categoryGoods = new ArrayList<>();
//            } else {
//                categoryGoods = goodsService.queryByCategory(l2List, 0, SystemConfig.getCatlogMoreLimit());
//            }
//
//            Map<String, Object> catGoods = new HashMap<>();
//            catGoods.put("id", catL1.getId());
//            catGoods.put("name", catL1.getName());
//            catGoods.put("goodsList", categoryGoods);
//            categoryList.add(catGoods);
//        }
//        return categoryList;
//    }

    /**
     * 商城介绍信息
     * @return 商城介绍信息
     */
//    @GetMapping("/about")
//    public Object about() {
//        Map<String, Object> about = new HashMap<>();
//        about.put("name", SystemConfig.getMallName());
//        about.put("address", SystemConfig.getMallAddress());
//        about.put("phone", SystemConfig.getMallPhone());
//        about.put("qq", SystemConfig.getMallQQ());
//        about.put("longitude", SystemConfig.getMallLongitude());
//        about.put("latitude", SystemConfig.getMallLatitude());
//        return ResponseUtil.ok(about);
//    }
}