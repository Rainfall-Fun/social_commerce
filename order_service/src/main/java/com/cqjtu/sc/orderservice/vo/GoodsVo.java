package com.cqjtu.sc.orderservice.vo;

import java.math.BigDecimal;
import java.util.Arrays;

public class GoodsVo {
    private Integer goodsId;
    private Integer productId;
    private String goodsName;
    private String[] specifications;
    private String goodsPic;
    private BigDecimal price;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String[] specifications) {
        this.specifications = specifications;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    @Override
    public String toString() {
        return "GoodsVo{" +
                "goodsId=" + goodsId +
                ", productId=" + productId +
                ", goodsName='" + goodsName + '\'' +
                ", specifications=" + Arrays.toString(specifications) +
                ", goodsPic='" + goodsPic + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
}
