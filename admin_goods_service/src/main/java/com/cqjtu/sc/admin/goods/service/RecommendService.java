package com.cqjtu.sc.admin.goods.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="goods-recommend-service")
public interface RecommendService {

    @GetMapping("getRecommendGoods")
    Object getRecommend(@RequestParam("userInfoId") Integer userInfoId);
    @GetMapping("getSupplierOtherGoods")
    Object getSupplierOtherGoods(@RequestParam("userInfoId") Integer userInfoId,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit);

}
