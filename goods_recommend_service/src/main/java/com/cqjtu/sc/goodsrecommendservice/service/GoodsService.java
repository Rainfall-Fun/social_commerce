package com.cqjtu.sc.goodsrecommendservice.service;

import com.cqjtu.sc.goodsrecommendservice.dto.BriefGoods;
import com.cqjtu.sc.goodsrecommendservice.service.fallback.GoodsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

@FeignClient(name="admin-goods-service",fallback = GoodsServiceFallback.class)
public interface GoodsService {
    @PostMapping("wx/goods/getBriefGoods")
    List<BriefGoods> getBriefGoods(@RequestBody Collection<Integer> data);
}
