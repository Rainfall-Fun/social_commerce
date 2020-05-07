package com.cqjtu.sc.goodsrecommendservice.api;

import com.cqjtu.sc.goodsrecommendservice.db.domain.AllGoodsInfo;
import com.cqjtu.sc.goodsrecommendservice.db.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class GoodsController {
    @Autowired
    GoodsInfoService goodsInfoService;
    @GetMapping("getRecommendGoods")
    public Object getRecommendGoods(Integer userInfoId){
        Set<Integer> goodsIds=new HashSet<>();
        //查询出点击量前三的商品
        List<Integer> browsing = goodsInfoService.queryByBrowsingTimes(1, 3);
        //查询出该用户所在区域的销售前三的商品
        List<Integer> region = goodsInfoService.queryByRegion(3);
        //查询销量前三的商品
        List<Integer> sale = goodsInfoService.queryBySale();
        goodsIds.addAll(region);
        goodsIds.addAll(sale);
        goodsIds.addAll(browsing);
        //不足十个商品时从推荐商品中获取剩余的商品
        List<Integer> recommend = goodsInfoService.queryByRecommend(1, 10-goodsIds.size(),goodsIds);
        goodsIds.addAll(recommend);

        //从商品服务中获取商品信息
        return goodsIds;
    }
}
