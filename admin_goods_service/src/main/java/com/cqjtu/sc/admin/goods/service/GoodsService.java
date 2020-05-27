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
            img.setType(1);//1为商品展示图
            imgs.add(img);
        }

        AllGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        Map<String,Integer> specifiIdMap=new HashMap<>();
        for (int i = 0; i < specifications.length; i++) {
            specifications[i].setGoodsId(goodsId);
            int specificationId = specificationService.add(specifications[i]);
            AllGoodsSpecifiImg img=new AllGoodsSpecifiImg();
            img.setGoodsId(goodsId);
            img.setId(specificationId);
            img.setUrl(specifications[i].getUrl()+"");
            img.setType(2);//2为规格图
            imgs.add(img);
            specifiIdMap.put(specifications[i].getSpecifiValue(),specificationId);
        }
        AllGoodsSpecifiImg img=new AllGoodsSpecifiImg();
        img.setGoodsId(goodsId);
        img.setType(3);//三为封面图
        img.setUrl(goods.getCoverImg());
        if (goods.getCoverImg()==null||goods.getCoverImg().equals(""))
            img.setUrl("default.jpg");
        imgs.add(img);
        imgService.batchAdd(imgs);

        AllGoodsSpecifiAttValue[] products = goodsAllinone.getProducts();
        List<AllGoodsSpecifiAttValue> ps=new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            AllGoodsSpecifiAttValue product = products[i];
            String[] specifications1 = product.getSpecifications();
            product.setSpecifications(specifications1);
            product.setGoodsId(goodsId);
            ps.add(product);
        }
        productService.batchInsert(ps);
        return ResponseUtil.ok();
    }
}
