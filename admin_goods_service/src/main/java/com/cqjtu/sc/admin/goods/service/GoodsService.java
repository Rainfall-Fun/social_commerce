package com.cqjtu.sc.admin.goods.service;

import com.cqjtu.sc.admin.goods.db.domain.AllGoodsInfo;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiImg;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecification;
import com.cqjtu.sc.admin.goods.db.service.AllGoodsInfoService;
import com.cqjtu.sc.admin.goods.db.service.GoodsImgService;
import com.cqjtu.sc.admin.goods.db.service.ProductService;
import com.cqjtu.sc.admin.goods.db.service.SpecificationService;
import com.cqjtu.sc.admin.goods.dto.GoodsAllinone;
import com.cqjtu.sc.admin.goods.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {
    @Autowired
    AllGoodsInfoService allGoodsInfoService;
    @Autowired
    GoodsImgService imgService;
    @Autowired
    SpecificationService specificationService;
    @Autowired
    ProductService productService;
    @Transactional
    public Object addGoods(GoodsAllinone goodsAllinone){
        List<AllGoodsSpecifiImg> imgs=new ArrayList<>();
        AllGoodsInfo goods = goodsAllinone.getGoods();
        goods.setPaymentId(1);
        goods.setCategoryId(1);
        goods.setGoodsAddTime(LocalDateTime.now());
        goods.setGoodsLastModifyedTime(LocalDateTime.now());
        goods.setGoodsLastRecommandTime(LocalDateTime.now());
        goods.setGoodsLastSetTime(LocalDateTime.now());
        int goodsId = allGoodsInfoService.add(goods);
        String[] gallery = goods.getGallery();
        for (int i = 0; i < gallery.length; i++) {
            AllGoodsSpecifiImg img=new AllGoodsSpecifiImg();
            img.setGoodsId(goodsId);
            imgs.add(img);
        }

        AllGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        Map<String,Integer> specifiIdMap=new HashMap<>();
        for (int i = 0; i < specifications.length; i++) {
            specifications[i].setGoodsId(goodsId);
            int specificationId = specificationService.add(specifications[i]);
            AllGoodsSpecifiImg img=new AllGoodsSpecifiImg();
            img.setGoodsId(goodsId);
            img.setSpecifiValueId(specificationId);
            img.setUrl(specifications[i].getUrl()+"");
            imgs.add(img);
            specifiIdMap.put(specifications[i].getSpecifiValue(),specificationId);
        }
        imgService.batchAdd(imgs);

        AllGoodsSpecifiAttValue[] products = goodsAllinone.getProducts();
        List<AllGoodsSpecifiAttValue> ps=new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            AllGoodsSpecifiAttValue product = products[i];
            String[] specifications1 = product.getSpecifications();
            String[] specifiIds=new String[specifications1.length];
            for (int i1 = 0; i1 < specifications1.length; i1++) {
                specifiIds[i1]=String.valueOf(specifiIdMap.get(specifications1[i1]));
            }
            product.setSpecifications(specifiIds);
            ps.add(product);
        }
        productService.batchInsert(ps);
        return ResponseUtil.ok();
    }
}
