package com.cqjtu.sc.gateway.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="goods-recommend-service")
public interface RecommendService {

    @GetMapping("getRecommendGoodsList")
    Object getRecommendGoodsList(@RequestParam("recommendType") Integer recommandType,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer limit);
}
