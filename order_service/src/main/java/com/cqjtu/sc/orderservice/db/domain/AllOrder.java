package com.cqjtu.sc.orderservice.db.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class AllOrder {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.order_id
     *
     * @mbg.generated
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.user_info_id
     *
     * @mbg.generated
     */
    private Integer userInfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.order_number
     *
     * @mbg.generated
     */
    private String orderNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.amount
     *
     * @mbg.generated
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.coupon_amount
     *
     * @mbg.generated
     */
    private BigDecimal couponAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.points_amount
     *
     * @mbg.generated
     */
    private BigDecimal pointsAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.gen_time
     *
     * @mbg.generated
     */
    private LocalDateTime genTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.address_str
     *
     * @mbg.generated
     */
    private String addressStr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.order_status
     *
     * @mbg.generated
     */
    private Integer orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_order.pay_time
     *
     * @mbg.generated
     */
    private LocalDateTime payTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.order_id
     *
     * @return the value of all_order.order_id
     *
     * @mbg.generated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.order_id
     *
     * @param orderId the value for all_order.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.user_info_id
     *
     * @return the value of all_order.user_info_id
     *
     * @mbg.generated
     */
    public Integer getUserInfoId() {
        return userInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.user_info_id
     *
     * @param userInfoId the value for all_order.user_info_id
     *
     * @mbg.generated
     */
    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.order_number
     *
     * @return the value of all_order.order_number
     *
     * @mbg.generated
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.order_number
     *
     * @param orderNumber the value for all_order.order_number
     *
     * @mbg.generated
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.amount
     *
     * @return the value of all_order.amount
     *
     * @mbg.generated
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.amount
     *
     * @param amount the value for all_order.amount
     *
     * @mbg.generated
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.coupon_amount
     *
     * @return the value of all_order.coupon_amount
     *
     * @mbg.generated
     */
    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.coupon_amount
     *
     * @param couponAmount the value for all_order.coupon_amount
     *
     * @mbg.generated
     */
    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.points_amount
     *
     * @return the value of all_order.points_amount
     *
     * @mbg.generated
     */
    public BigDecimal getPointsAmount() {
        return pointsAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.points_amount
     *
     * @param pointsAmount the value for all_order.points_amount
     *
     * @mbg.generated
     */
    public void setPointsAmount(BigDecimal pointsAmount) {
        this.pointsAmount = pointsAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.gen_time
     *
     * @return the value of all_order.gen_time
     *
     * @mbg.generated
     */
    public LocalDateTime getGenTime() {
        return genTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.gen_time
     *
     * @param genTime the value for all_order.gen_time
     *
     * @mbg.generated
     */
    public void setGenTime(LocalDateTime genTime) {
        this.genTime = genTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.address_str
     *
     * @return the value of all_order.address_str
     *
     * @mbg.generated
     */
    public String getAddressStr() {
        return addressStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.address_str
     *
     * @param addressStr the value for all_order.address_str
     *
     * @mbg.generated
     */
    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.order_status
     *
     * @return the value of all_order.order_status
     *
     * @mbg.generated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.order_status
     *
     * @param orderStatus the value for all_order.order_status
     *
     * @mbg.generated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_order.pay_time
     *
     * @return the value of all_order.pay_time
     *
     * @mbg.generated
     */
    public LocalDateTime getPayTime() {
        return payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_order.pay_time
     *
     * @param payTime the value for all_order.pay_time
     *
     * @mbg.generated
     */
    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", amount=").append(amount);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", pointsAmount=").append(pointsAmount);
        sb.append(", genTime=").append(genTime);
        sb.append(", addressStr=").append(addressStr);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", payTime=").append(payTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AllOrder other = (AllOrder) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getUserInfoId() == null ? other.getUserInfoId() == null : this.getUserInfoId().equals(other.getUserInfoId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCouponAmount() == null ? other.getCouponAmount() == null : this.getCouponAmount().equals(other.getCouponAmount()))
            && (this.getPointsAmount() == null ? other.getPointsAmount() == null : this.getPointsAmount().equals(other.getPointsAmount()))
            && (this.getGenTime() == null ? other.getGenTime() == null : this.getGenTime().equals(other.getGenTime()))
            && (this.getAddressStr() == null ? other.getAddressStr() == null : this.getAddressStr().equals(other.getAddressStr()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserInfoId() == null) ? 0 : getUserInfoId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCouponAmount() == null) ? 0 : getCouponAmount().hashCode());
        result = prime * result + ((getPointsAmount() == null) ? 0 : getPointsAmount().hashCode());
        result = prime * result + ((getGenTime() == null) ? 0 : getGenTime().hashCode());
        result = prime * result + ((getAddressStr() == null) ? 0 : getAddressStr().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table all_order
     *
     * @mbg.generated
     */
    public enum Column {
        orderId("order_id", "orderId", "INTEGER", false),
        userInfoId("user_info_id", "userInfoId", "INTEGER", false),
        orderNumber("order_number", "orderNumber", "VARCHAR", false),
        amount("amount", "amount", "DECIMAL", false),
        couponAmount("coupon_amount", "couponAmount", "DECIMAL", false),
        pointsAmount("points_amount", "pointsAmount", "DECIMAL", false),
        genTime("gen_time", "genTime", "TIMESTAMP", false),
        addressStr("address_str", "addressStr", "VARCHAR", false),
        orderStatus("order_status", "orderStatus", "INTEGER", false),
        payTime("pay_time", "payTime", "TIMESTAMP", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_order
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_order
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_order
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_order
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_order
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_order
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_order
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}