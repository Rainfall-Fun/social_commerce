package com.cqjtu.sc.admin.goods.db.domain;

import java.util.ArrayList;
import java.util.List;

public class AllGoodsBoardExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public AllGoodsBoardExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
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
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public AllGoodsBoardExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public AllGoodsBoardExample orderBy(String ... orderByClauses) {
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
     * This method corresponds to the database table all_goods_board
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
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
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
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public static Criteria newAndCreateCriteria() {
        AllGoodsBoardExample example = new AllGoodsBoardExample();
        return example.createCriteria();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public AllGoodsBoardExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public AllGoodsBoardExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table all_goods_board
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

        public Criteria andBoardIdIsNull() {
            addCriterion("board_id is null");
            return (Criteria) this;
        }

        public Criteria andBoardIdIsNotNull() {
            addCriterion("board_id is not null");
            return (Criteria) this;
        }

        public Criteria andBoardIdEqualTo(Integer value) {
            addCriterion("board_id =", value, "boardId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardIdEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardIdNotEqualTo(Integer value) {
            addCriterion("board_id <>", value, "boardId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardIdNotEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardIdGreaterThan(Integer value) {
            addCriterion("board_id >", value, "boardId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardIdGreaterThanColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("board_id >=", value, "boardId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardIdGreaterThanOrEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardIdLessThan(Integer value) {
            addCriterion("board_id <", value, "boardId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardIdLessThanColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardIdLessThanOrEqualTo(Integer value) {
            addCriterion("board_id <=", value, "boardId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardIdLessThanOrEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardIdIn(List<Integer> values) {
            addCriterion("board_id in", values, "boardId");
            return (Criteria) this;
        }

        public Criteria andBoardIdNotIn(List<Integer> values) {
            addCriterion("board_id not in", values, "boardId");
            return (Criteria) this;
        }

        public Criteria andBoardIdBetween(Integer value1, Integer value2) {
            addCriterion("board_id between", value1, value2, "boardId");
            return (Criteria) this;
        }

        public Criteria andBoardIdNotBetween(Integer value1, Integer value2) {
            addCriterion("board_id not between", value1, value2, "boardId");
            return (Criteria) this;
        }

        public Criteria andBoardIsNull() {
            addCriterion("board is null");
            return (Criteria) this;
        }

        public Criteria andBoardIsNotNull() {
            addCriterion("board is not null");
            return (Criteria) this;
        }

        public Criteria andBoardEqualTo(String value) {
            addCriterion("board =", value, "board");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardNotEqualTo(String value) {
            addCriterion("board <>", value, "board");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardNotEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardGreaterThan(String value) {
            addCriterion("board >", value, "board");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardGreaterThanColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardGreaterThanOrEqualTo(String value) {
            addCriterion("board >=", value, "board");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardGreaterThanOrEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardLessThan(String value) {
            addCriterion("board <", value, "board");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardLessThanColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardLessThanOrEqualTo(String value) {
            addCriterion("board <=", value, "board");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andBoardLessThanOrEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("board <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBoardLike(String value) {
            addCriterion("board like", value, "board");
            return (Criteria) this;
        }

        public Criteria andBoardNotLike(String value) {
            addCriterion("board not like", value, "board");
            return (Criteria) this;
        }

        public Criteria andBoardIn(List<String> values) {
            addCriterion("board in", values, "board");
            return (Criteria) this;
        }

        public Criteria andBoardNotIn(List<String> values) {
            addCriterion("board not in", values, "board");
            return (Criteria) this;
        }

        public Criteria andBoardBetween(String value1, String value2) {
            addCriterion("board between", value1, value2, "board");
            return (Criteria) this;
        }

        public Criteria andBoardNotBetween(String value1, String value2) {
            addCriterion("board not between", value1, value2, "board");
            return (Criteria) this;
        }

        public Criteria andDesIsNull() {
            addCriterion("des is null");
            return (Criteria) this;
        }

        public Criteria andDesIsNotNull() {
            addCriterion("des is not null");
            return (Criteria) this;
        }

        public Criteria andDesEqualTo(String value) {
            addCriterion("des =", value, "des");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andDesEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("des = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDesNotEqualTo(String value) {
            addCriterion("des <>", value, "des");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andDesNotEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("des <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDesGreaterThan(String value) {
            addCriterion("des >", value, "des");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andDesGreaterThanColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("des > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDesGreaterThanOrEqualTo(String value) {
            addCriterion("des >=", value, "des");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andDesGreaterThanOrEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("des >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDesLessThan(String value) {
            addCriterion("des <", value, "des");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andDesLessThanColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("des < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDesLessThanOrEqualTo(String value) {
            addCriterion("des <=", value, "des");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public Criteria andDesLessThanOrEqualToColumn(AllGoodsBoard.Column column) {
            addCriterion(new StringBuilder("des <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDesLike(String value) {
            addCriterion("des like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotLike(String value) {
            addCriterion("des not like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesIn(List<String> values) {
            addCriterion("des in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotIn(List<String> values) {
            addCriterion("des not in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesBetween(String value1, String value2) {
            addCriterion("des between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotBetween(String value1, String value2) {
            addCriterion("des not between", value1, value2, "des");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table all_goods_board
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        private AllGoodsBoardExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        protected Criteria(AllGoodsBoardExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public AllGoodsBoardExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
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
         * This method corresponds to the database table all_goods_board
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
         * This method corresponds to the database table all_goods_board
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
             * This method corresponds to the database table all_goods_board
             *
             * @mbg.generated
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table all_goods_board
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
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        void example(com.cqjtu.sc.admin.goods.db.domain.AllGoodsBoardExample example);
    }
}