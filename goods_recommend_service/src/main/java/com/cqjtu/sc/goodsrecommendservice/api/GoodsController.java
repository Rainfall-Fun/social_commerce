package com.cqjtu.sc.goodsrecommendservice.api;

import com.cqjtu.sc.goodsrecommendservice.db.service.RecommendService;
import com.cqjtu.sc.goodsrecommendservice.dto.BriefGoods;
import com.cqjtu.sc.goodsrecommendservice.service.GoodsService;
import com.cqjtu.sc.goodsrecommendservice.util.ResponseUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
public class GoodsController {
    @Autowired
    RecommendService recommendService;
    @Autowired
    GoodsService goodsService;

    @GetMapping("getRecommendGoods")
    public Object getRecommendGoods(Integer userInfoId){
        Set<Integer> goodsIds=new HashSet<>();
        //查询出点击量前三的商品
        List<Integer> browsing = recommendService.queryByBrowsingTimes(1, 3);
        //查询出该用户所在区域的销售前三的商品
        List<Integer> region = recommendService.queryByRegion(0,3,userInfoId);
        //查询销量前三的商品
        List<Integer> sale = recommendService.queryBySale(0,3);
        //查询出好评前三的商品
        List<Integer> ratio = recommendService.queryByRatio(0,3);
        goodsIds.addAll(region);
        goodsIds.addAll(sale);
        goodsIds.addAll(browsing);
        goodsIds.addAll(ratio);
        //不足十个商品时从推荐商品中获取剩余的商品
        List<Integer> recommend = recommendService.queryByRecommend(0, 10-goodsIds.size(),goodsIds);
        goodsIds.addAll(recommend);
        //从商品服务中获取商品信息
        return goodsIds;
    }

    @GetMapping("getRecommendGoodsList")
    public Object getRecommendGoodsList(Integer userInfoId,Integer recommendType,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer limit){
        List<Integer> goodsId=null;
        int pages=0;
        switch (recommendType){
            case 0:
                goodsId= recommendService.queryByBrowsingTimes((page-1)*limit, limit);
                if (goodsId instanceof Page){
                    Page page1=(Page) goodsId;
                    pages=page1.getPages();
                }
                break;
            case 1:
                goodsId= recommendService.queryBySale(page,limit);
                pages= recommendService.countBySale(limit);
                break;
            case 2:
                goodsId= recommendService.queryByRatio(page,limit);
                pages= recommendService.countByRatio(limit);
                break;
            case 3:
                goodsId= recommendService.queryByRegion(page,userInfoId,limit);
                pages= recommendService.countByRegion(limit,userInfoId);
                break;
        }


        Page<BriefGoods> data=new Page<>();
        data.setPages(pages);
        data.setPageNum(page);
        data.addAll(goodsService.getBriefGoods(goodsId));
        for (BriefGoods datum : data) {
            datum.setCounterPrice(datum.getRetailPrice().multiply(BigDecimal.valueOf(1.5)));
            datum.setPicUrl("http://localhost:8777/"+datum.getPicUrl());
        }
        return ResponseUtil.okList(data);
    }

}
