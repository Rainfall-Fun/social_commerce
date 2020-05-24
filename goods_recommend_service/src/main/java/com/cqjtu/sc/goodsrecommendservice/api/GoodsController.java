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
        List<Integer> goodsIds = recommendService.queryAllInOne(userInfoId);
        List<Integer> recommend = recommendService.queryByRecommend(0, 10-goodsIds.size(),goodsIds);
        goodsIds.addAll(recommend);
        if (goodsIds.size()==0)
            return null;
        //从商品服务中获取商品信息
        List<BriefGoods> briefGoods = goodsService.getBriefGoods(goodsIds);
        return briefGoods;
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
        if (goodsId.size()==0){
            Page<BriefGoods> data=new Page<>();
            data.setPages(0);
            data.setPageNum(page);
            return ResponseUtil.okList(data);
        }
        Page<BriefGoods> data=new Page<>();
        data.setPages(pages);
        data.setPageNum(page);
        data.addAll(goodsService.getBriefGoods(goodsId));
        return ResponseUtil.okList(data);
    }


    @GetMapping("getSupplierOtherGoods")
    public Object getSupplierOtherGoods(Integer userInfoId,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer limit){
        List<Integer> goodsId=null;
        int pages=0;
        goodsId=recommendService.queryBySupplier(page,userInfoId,limit);
        if (goodsId.size()==0){
            Page<BriefGoods> data=new Page<>();
            data.setPages(0);
            data.setPageNum(page);
            return ResponseUtil.okList(data);
        }
        pages=recommendService.countBySupplier(limit,userInfoId);
        Page<BriefGoods> data=new Page<>();
        data.setPages(pages);
        data.setPageNum(page);
        data.addAll(goodsService.getBriefGoods(goodsId));
        return ResponseUtil.okList(data);
    }

}
