package com.cqjtu.sc.orderservice.dto;

public class PurchaseGoodsDto {
    Integer goodsId;
    Integer number;
    Integer productId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "PurchaseGoodsDto{" +
                "goodsId=" + goodsId +
                ", number=" + number +
                ", productId=" + productId +
                '}';
    }
}
