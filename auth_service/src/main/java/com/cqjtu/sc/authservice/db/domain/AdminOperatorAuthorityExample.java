package com.cqjtu.sc.authservice.db.domain;

import java.util.ArrayList;
import java.util.List;

public class AdminOperatorAuthorityExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public AdminOperatorAuthorityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public AdminOperatorAuthorityExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public AdminOperatorAuthorityExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public static Criteria newAndCreateCriteria() {
        AdminOperatorAuthorityExample example = new AdminOperatorAuthorityExample();
        return example.createCriteria();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public AdminOperatorAuthorityExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public AdminOperatorAuthorityExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOperatorAuthIdIsNull() {
            addCriterion("operator_auth_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdIsNotNull() {
            addCriterion("operator_auth_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdEqualTo(Integer value) {
            addCriterion("operator_auth_id =", value, "operatorAuthId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorAuthIdEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_auth_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdNotEqualTo(Integer value) {
            addCriterion("operator_auth_id <>", value, "operatorAuthId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorAuthIdNotEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_auth_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdGreaterThan(Integer value) {
            addCriterion("operator_auth_id >", value, "operatorAuthId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorAuthIdGreaterThanColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_auth_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_auth_id >=", value, "operatorAuthId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorAuthIdGreaterThanOrEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_auth_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdLessThan(Integer value) {
            addCriterion("operator_auth_id <", value, "operatorAuthId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorAuthIdLessThanColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_auth_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdLessThanOrEqualTo(Integer value) {
            addCriterion("operator_auth_id <=", value, "operatorAuthId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorAuthIdLessThanOrEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_auth_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdIn(List<Integer> values) {
            addCriterion("operator_auth_id in", values, "operatorAuthId");
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdNotIn(List<Integer> values) {
            addCriterion("operator_auth_id not in", values, "operatorAuthId");
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdBetween(Integer value1, Integer value2) {
            addCriterion("operator_auth_id between", value1, value2, "operatorAuthId");
            return (Criteria) this;
        }

        public Criteria andOperatorAuthIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_auth_id not between", value1, value2, "operatorAuthId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(Integer value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorIdEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(Integer value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorIdNotEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(Integer value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorIdGreaterThanColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorIdGreaterThanOrEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(Integer value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorIdLessThanColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andOperatorIdLessThanOrEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("operator_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<Integer> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<Integer> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdIsNull() {
            addCriterion("authority_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdIsNotNull() {
            addCriterion("authority_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdEqualTo(Integer value) {
            addCriterion("authority_id =", value, "authorityId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andAuthorityIdEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("authority_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotEqualTo(Integer value) {
            addCriterion("authority_id <>", value, "authorityId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andAuthorityIdNotEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("authority_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAuthorityIdGreaterThan(Integer value) {
            addCriterion("authority_id >", value, "authorityId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andAuthorityIdGreaterThanColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("authority_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAuthorityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("authority_id >=", value, "authorityId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andAuthorityIdGreaterThanOrEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("authority_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAuthorityIdLessThan(Integer value) {
            addCriterion("authority_id <", value, "authorityId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andAuthorityIdLessThanColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("authority_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAuthorityIdLessThanOrEqualTo(Integer value) {
            addCriterion("authority_id <=", value, "authorityId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria andAuthorityIdLessThanOrEqualToColumn(AdminOperatorAuthority.Column column) {
            addCriterion(new StringBuilder("authority_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAuthorityIdIn(List<Integer> values) {
            addCriterion("authority_id in", values, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotIn(List<Integer> values) {
            addCriterion("authority_id not in", values, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdBetween(Integer value1, Integer value2) {
            addCriterion("authority_id between", value1, value2, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("authority_id not between", value1, value2, "authorityId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table admin_operator_authority
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        private AdminOperatorAuthorityExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        protected Criteria(AdminOperatorAuthorityExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public AdminOperatorAuthorityExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        @Deprecated
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            } else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {
            /**
             * This method was generated by MyBatis Generator.
             * This method corresponds to the database table admin_operator_authority
             *
             * @mbg.generated
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public interface ICriteriaWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table admin_operator_authority
         *
         * @mbg.generated
         */
        void example(com.cqjtu.sc.authservice.db.domain.AdminOperatorAuthorityExample example);
    }
}