package com.cqjtu.sc.admin.goods.vo;

import lombok.Data;

@Data
public class GoodsVo {
    private int goodsId;//商品id
    private String name;//商品名称
//    private double counterPrice;//市场价格
    private double retailPrice;//当前价格
//    private double costPrice;//成本价
    private double taxCost;//税费
    private int isRecommendation;//是否是推荐商品吗，1：是
    private int isOnSale;//是否上架，1：是
    private String picUrl;//商品封面图地址
    private String unit;//商品计量单位
    private int brandId;//所属品牌id
    private String supplierId;//供应商id
    private int belongModule;
    private String brief;//简短介绍
    private String detail;//详细介绍
    private String categoryId;//商品分类
    private int paymentWay;//支付方式
    private int freightRule;//运费规则
    private String[] gallery;
}
