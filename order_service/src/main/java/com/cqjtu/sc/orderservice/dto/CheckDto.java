package com.cqjtu.sc.orderservice.dto;

public class CheckDto {
    PurchaseGoodsDto [] purchaseGoods;

    public PurchaseGoodsDto[] getPurchaseGoods() {
        return purchaseGoods;
    }

    public void setPurchaseGoods(PurchaseGoodsDto[] purchaseGoods) {
        this.purchaseGoods = purchaseGoods;
    }
}
