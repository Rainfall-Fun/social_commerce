package com.cqjtu.sc.admin.goods.db.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class AllCarrigeRuleConsist {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_carrige_rule_consist.carrige_rule_id
     *
     * @mbg.generated
     */
    private Integer carrigeRuleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_carrige_rule_consist.carriage_rule_id
     *
     * @mbg.generated
     */
    private Integer carriageRuleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_carrige_rule_consist.carrige_rule
     *
     * @mbg.generated
     */
    private String carrigeRule;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_carrige_rule_consist.cost
     *
     * @mbg.generated
     */
    private BigDecimal cost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_carrige_rule_consist.setting_time
     *
     * @mbg.generated
     */
    private LocalDate settingTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_carrige_rule_consist.carrige_is_valid
     *
     * @mbg.generated
     */
    private Integer carrigeIsValid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_carrige_rule_consist.carrige_rule_id
     *
     * @return the value of all_carrige_rule_consist.carrige_rule_id
     *
     * @mbg.generated
     */
    public Integer getCarrigeRuleId() {
        return carrigeRuleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_carrige_rule_consist.carrige_rule_id
     *
     * @param carrigeRuleId the value for all_carrige_rule_consist.carrige_rule_id
     *
     * @mbg.generated
     */
    public void setCarrigeRuleId(Integer carrigeRuleId) {
        this.carrigeRuleId = carrigeRuleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_carrige_rule_consist.carriage_rule_id
     *
     * @return the value of all_carrige_rule_consist.carriage_rule_id
     *
     * @mbg.generated
     */
    public Integer getCarriageRuleId() {
        return carriageRuleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_carrige_rule_consist.carriage_rule_id
     *
     * @param carriageRuleId the value for all_carrige_rule_consist.carriage_rule_id
     *
     * @mbg.generated
     */
    public void setCarriageRuleId(Integer carriageRuleId) {
        this.carriageRuleId = carriageRuleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_carrige_rule_consist.carrige_rule
     *
     * @return the value of all_carrige_rule_consist.carrige_rule
     *
     * @mbg.generated
     */
    public String getCarrigeRule() {
        return carrigeRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_carrige_rule_consist.carrige_rule
     *
     * @param carrigeRule the value for all_carrige_rule_consist.carrige_rule
     *
     * @mbg.generated
     */
    public void setCarrigeRule(String carrigeRule) {
        this.carrigeRule = carrigeRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_carrige_rule_consist.cost
     *
     * @return the value of all_carrige_rule_consist.cost
     *
     * @mbg.generated
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_carrige_rule_consist.cost
     *
     * @param cost the value for all_carrige_rule_consist.cost
     *
     * @mbg.generated
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_carrige_rule_consist.setting_time
     *
     * @return the value of all_carrige_rule_consist.setting_time
     *
     * @mbg.generated
     */
    public LocalDate getSettingTime() {
        return settingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_carrige_rule_consist.setting_time
     *
     * @param settingTime the value for all_carrige_rule_consist.setting_time
     *
     * @mbg.generated
     */
    public void setSettingTime(LocalDate settingTime) {
        this.settingTime = settingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_carrige_rule_consist.carrige_is_valid
     *
     * @return the value of all_carrige_rule_consist.carrige_is_valid
     *
     * @mbg.generated
     */
    public Integer getCarrigeIsValid() {
        return carrigeIsValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_carrige_rule_consist.carrige_is_valid
     *
     * @param carrigeIsValid the value for all_carrige_rule_consist.carrige_is_valid
     *
     * @mbg.generated
     */
    public void setCarrigeIsValid(Integer carrigeIsValid) {
        this.carrigeIsValid = carrigeIsValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_rule_consist
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", carrigeRuleId=").append(carrigeRuleId);
        sb.append(", carriageRuleId=").append(carriageRuleId);
        sb.append(", carrigeRule=").append(carrigeRule);
        sb.append(", cost=").append(cost);
        sb.append(", settingTime=").append(settingTime);
        sb.append(", carrigeIsValid=").append(carrigeIsValid);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_rule_consist
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
        AllCarrigeRuleConsist other = (AllCarrigeRuleConsist) that;
        return (this.getCarrigeRuleId() == null ? other.getCarrigeRuleId() == null : this.getCarrigeRuleId().equals(other.getCarrigeRuleId()))
            && (this.getCarriageRuleId() == null ? other.getCarriageRuleId() == null : this.getCarriageRuleId().equals(other.getCarriageRuleId()))
            && (this.getCarrigeRule() == null ? other.getCarrigeRule() == null : this.getCarrigeRule().equals(other.getCarrigeRule()))
            && (this.getCost() == null ? other.getCost() == null : this.getCost().equals(other.getCost()))
            && (this.getSettingTime() == null ? other.getSettingTime() == null : this.getSettingTime().equals(other.getSettingTime()))
            && (this.getCarrigeIsValid() == null ? other.getCarrigeIsValid() == null : this.getCarrigeIsValid().equals(other.getCarrigeIsValid()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_rule_consist
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCarrigeRuleId() == null) ? 0 : getCarrigeRuleId().hashCode());
        result = prime * result + ((getCarriageRuleId() == null) ? 0 : getCarriageRuleId().hashCode());
        result = prime * result + ((getCarrigeRule() == null) ? 0 : getCarrigeRule().hashCode());
        result = prime * result + ((getCost() == null) ? 0 : getCost().hashCode());
        result = prime * result + ((getSettingTime() == null) ? 0 : getSettingTime().hashCode());
        result = prime * result + ((getCarrigeIsValid() == null) ? 0 : getCarrigeIsValid().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table all_carrige_rule_consist
     *
     * @mbg.generated
     */
    public enum Column {
        carrigeRuleId("carrige_rule_id", "carrigeRuleId", "INTEGER", false),
        carriageRuleId("carriage_rule_id", "carriageRuleId", "INTEGER", false),
        carrigeRule("carrige_rule", "carrigeRule", "VARCHAR", false),
        cost("cost", "cost", "DECIMAL", false),
        settingTime("setting_time", "settingTime", "DATE", false),
        carrigeIsValid("carrige_is_valid", "carrigeIsValid", "INTEGER", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_carrige_rule_consist
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
         * This method corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_carrige_rule_consist
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
         * This method corresponds to the database table all_carrige_rule_consist
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
         * This method corresponds to the database table all_carrige_rule_consist
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}