package com.cqjtu.sc.goodsrecommendservice.service;

import com.cqjtu.sc.goodsrecommendservice.dto.BriefGoods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="admin-goods-service")
public interface GoodsService {
    @PostMapping("wx/goods/getBriefGoods")
    List<BriefGoods> getBriefGoods(@RequestBody List<Integer> data);
}
