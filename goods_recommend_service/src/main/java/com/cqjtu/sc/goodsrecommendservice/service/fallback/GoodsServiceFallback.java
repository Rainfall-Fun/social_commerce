package com.cqjtu.sc.goodsrecommendservice.service.fallback;

import com.cqjtu.sc.goodsrecommendservice.dto.BriefGoods;
import com.cqjtu.sc.goodsrecommendservice.service.GoodsService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class GoodsServiceFallback implements GoodsService {
    @Override
    public List<BriefGoods> getBriefGoods(Collection<Integer> data) {
        List<BriefGoods> briefGoods=new ArrayList<>();
        BriefGoods briefGoods1=new BriefGoods();
        briefGoods1.setId(0);
        briefGoods1.setName("请稍后再试");
        briefGoods1.setCounterPrice(new BigDecimal(0.00));
        briefGoods1.setRetailPrice(new BigDecimal(0.00));
        briefGoods1.setPicUrl("http://localhost:8777/default.jpg");
        briefGoods.add(briefGoods1);
        return briefGoods;
    }
}
