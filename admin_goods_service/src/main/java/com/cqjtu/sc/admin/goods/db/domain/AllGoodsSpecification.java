package com.cqjtu.sc.admin.goods.db.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class AllGoodsSpecification {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_goods_specification.goods_specifi_id
     *
     * @mbg.generated
     */
    private Integer goodsSpecifiId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_goods_specification.goods_id
     *
     * @mbg.generated
     */
    private Integer goodsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_goods_specification.specifi_name
     *
     * @mbg.generated
     */
    private String specifiName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_goods_specification.specifi_value
     *
     * @mbg.generated
     */
    private String specifiValue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_goods_specification.goods_specifi_id
     *
     * @return the value of all_goods_specification.goods_specifi_id
     *
     * @mbg.generated
     */
    public Integer getGoodsSpecifiId() {
        return goodsSpecifiId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_goods_specification.goods_specifi_id
     *
     * @param goodsSpecifiId the value for all_goods_specification.goods_specifi_id
     *
     * @mbg.generated
     */
    public void setGoodsSpecifiId(Integer goodsSpecifiId) {
        this.goodsSpecifiId = goodsSpecifiId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_goods_specification.goods_id
     *
     * @return the value of all_goods_specification.goods_id
     *
     * @mbg.generated
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_goods_specification.goods_id
     *
     * @param goodsId the value for all_goods_specification.goods_id
     *
     * @mbg.generated
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_goods_specification.specifi_name
     *
     * @return the value of all_goods_specification.specifi_name
     *
     * @mbg.generated
     */
    public String getSpecifiName() {
        return specifiName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_goods_specification.specifi_name
     *
     * @param specifiName the value for all_goods_specification.specifi_name
     *
     * @mbg.generated
     */
    public void setSpecifiName(String specifiName) {
        this.specifiName = specifiName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_goods_specification.specifi_value
     *
     * @return the value of all_goods_specification.specifi_value
     *
     * @mbg.generated
     */
    public String getSpecifiValue() {
        return specifiValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_goods_specification.specifi_value
     *
     * @param specifiValue the value for all_goods_specification.specifi_value
     *
     * @mbg.generated
     */
    public void setSpecifiValue(String specifiValue) {
        this.specifiValue = specifiValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_specification
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodsSpecifiId=").append(goodsSpecifiId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", specifiName=").append(specifiName);
        sb.append(", specifiValue=").append(specifiValue);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_specification
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
        AllGoodsSpecification other = (AllGoodsSpecification) that;
        return (this.getGoodsSpecifiId() == null ? other.getGoodsSpecifiId() == null : this.getGoodsSpecifiId().equals(other.getGoodsSpecifiId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getSpecifiName() == null ? other.getSpecifiName() == null : this.getSpecifiName().equals(other.getSpecifiName()))
            && (this.getSpecifiValue() == null ? other.getSpecifiValue() == null : this.getSpecifiValue().equals(other.getSpecifiValue()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_specification
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGoodsSpecifiId() == null) ? 0 : getGoodsSpecifiId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getSpecifiName() == null) ? 0 : getSpecifiName().hashCode());
        result = prime * result + ((getSpecifiValue() == null) ? 0 : getSpecifiValue().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table all_goods_specification
     *
     * @mbg.generated
     */
    public enum Column {
        goodsSpecifiId("goods_specifi_id", "goodsSpecifiId", "INTEGER", false),
        goodsId("goods_id", "goodsId", "INTEGER", false),
        specifiName("specifi_name", "specifiName", "VARCHAR", false),
        specifiValue("specifi_value", "specifiValue", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_specification
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
         * This method corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_specification
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
         * This method corresponds to the database table all_goods_specification
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
         * This method corresponds to the database table all_goods_specification
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}