package com.cqjtu.sc.admin.goods.dto;

import com.cqjtu.sc.admin.goods.db.domain.AllGoodsInfo;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecification;

public class GoodsAllinone {
    AllGoodsInfo goods;
    AllGoodsSpecification [] specifications;
    AllGoodsSpecifiAttValue [] products;

    public AllGoodsInfo getGoods() {
        return goods;
    }

    public void setGoods(AllGoodsInfo goods) {
        this.goods = goods;
    }

    public AllGoodsSpecification[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(AllGoodsSpecification[] specifications) {
        this.specifications = specifications;
    }

    public AllGoodsSpecifiAttValue[] getProducts() {
        return products;
    }

    public void setProducts(AllGoodsSpecifiAttValue[] products) {
        this.products = products;
    }
}
