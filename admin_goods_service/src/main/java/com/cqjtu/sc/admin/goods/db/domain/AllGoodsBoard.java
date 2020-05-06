package com.cqjtu.sc.admin.goods.db.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class AllGoodsBoard {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_goods_board.board_id
     *
     * @mbg.generated
     */
    private Integer boardId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_goods_board.board
     *
     * @mbg.generated
     */
    private String board;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_goods_board.des
     *
     * @mbg.generated
     */
    private String des;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_goods_board.board_id
     *
     * @return the value of all_goods_board.board_id
     *
     * @mbg.generated
     */
    public Integer getBoardId() {
        return boardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_goods_board.board_id
     *
     * @param boardId the value for all_goods_board.board_id
     *
     * @mbg.generated
     */
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_goods_board.board
     *
     * @return the value of all_goods_board.board
     *
     * @mbg.generated
     */
    public String getBoard() {
        return board;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_goods_board.board
     *
     * @param board the value for all_goods_board.board
     *
     * @mbg.generated
     */
    public void setBoard(String board) {
        this.board = board;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_goods_board.des
     *
     * @return the value of all_goods_board.des
     *
     * @mbg.generated
     */
    public String getDes() {
        return des;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_goods_board.des
     *
     * @param des the value for all_goods_board.des
     *
     * @mbg.generated
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", boardId=").append(boardId);
        sb.append(", board=").append(board);
        sb.append(", des=").append(des);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
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
        AllGoodsBoard other = (AllGoodsBoard) that;
        return (this.getBoardId() == null ? other.getBoardId() == null : this.getBoardId().equals(other.getBoardId()))
            && (this.getBoard() == null ? other.getBoard() == null : this.getBoard().equals(other.getBoard()))
            && (this.getDes() == null ? other.getDes() == null : this.getDes().equals(other.getDes()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBoardId() == null) ? 0 : getBoardId().hashCode());
        result = prime * result + ((getBoard() == null) ? 0 : getBoard().hashCode());
        result = prime * result + ((getDes() == null) ? 0 : getDes().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table all_goods_board
     *
     * @mbg.generated
     */
    public enum Column {
        boardId("board_id", "boardId", "INTEGER", false),
        board("board", "board", "VARCHAR", false),
        des("des", "des", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
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
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_goods_board
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
         * This method corresponds to the database table all_goods_board
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
         * This method corresponds to the database table all_goods_board
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}