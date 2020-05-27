package com.cqjtu.sc.orderservice.dto;

import com.cqjtu.sc.orderservice.vo.GoodsVo;

public class PurchaseOrderDto {
    GoodsVo []purchaseProducts;
    int addressId;

    public GoodsVo[] getPurchaseProducts() {
        return purchaseProducts;
    }

    public void setPurchaseProducts(GoodsVo[] purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
