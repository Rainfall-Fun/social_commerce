package com.cqjtu.sc.orderservice.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderVo {
    private int id;
    private String userId;
    private String orderSn;
    private int orderStatus;
    private BigDecimal orderPrice;
    private BigDecimal actualPrice;
    private Date payTime;
    private String shipSn;
    private String shipChannel;
    private Date genTime;

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getShipSn() {
        return shipSn;
    }

    public void setShipSn(String shipSn) {
        this.shipSn = shipSn;
    }

    public String getShipChannel() {
        return shipChannel;
    }

    public void setShipChannel(String shipChannel) {
        this.shipChannel = shipChannel;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", orderSn='" + orderSn + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderPrice=" + orderPrice +
                ", actualPrice=" + actualPrice +
                ", payTime=" + payTime +
                ", shipSn='" + shipSn + '\'' +
                ", shipChannel='" + shipChannel + '\'' +
                ", genTime=" + genTime +
                '}';
    }
}
