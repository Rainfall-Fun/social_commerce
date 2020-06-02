package com.cqjtu.sc.orderservice.db.dao;

import com.cqjtu.sc.orderservice.db.domain.AllOrderDetail;
import com.cqjtu.sc.orderservice.db.domain.AllOrderDetailExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cqjtu.sc.orderservice.db.domain.UnpaidOrderInfo;
import com.cqjtu.sc.orderservice.util.CountGoodsStatus;
import com.cqjtu.sc.orderservice.vo.CommentGoodsVo;
import com.cqjtu.sc.orderservice.vo.OrderDetailVo;
import com.cqjtu.sc.orderservice.vo.OrderVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AllOrderDetailMapper {
    CommentGoodsVo getCommentGoodsInfo(Integer userId,Integer orderId);
    int countByUserIdAndStatus(Integer userId, List<Integer> status,int limit);
    Page<UnpaidOrderInfo> selectByUserIdAndStatus(Integer userId, List<Integer> status, int offset, int limit);
    List<UnpaidOrderInfo> selectUnpaidOrderInfo(Integer userId, List<Integer> orderIdList);
    List<CountGoodsStatus> countForGoodsStatus(Integer userId);
    OrderDetailVo selectOrderDetail(int userId);
    List<OrderVo> selectOrder(Integer userId,List<Integer> goodsStatus,String orderSn,Integer supplierId);
    void batchInsertOrderDetail(List<AllOrderDetail> list);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    long countByExample(AllOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    int deleteByExample(AllOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer orderDetailId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    int insert(AllOrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    int insertSelective(AllOrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    AllOrderDetail selectOneByExample(AllOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    AllOrderDetail selectOneByExampleSelective(@Param("example") AllOrderDetailExample example, @Param("selective") AllOrderDetail.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    List<AllOrderDetail> selectByExampleSelective(@Param("example") AllOrderDetailExample example, @Param("selective") AllOrderDetail.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    List<AllOrderDetail> selectByExample(AllOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    AllOrderDetail selectByPrimaryKeySelective(@Param("orderDetailId") Integer orderDetailId, @Param("selective") AllOrderDetail.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    AllOrderDetail selectByPrimaryKey(Integer orderDetailId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AllOrderDetail record, @Param("example") AllOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AllOrderDetail record, @Param("example") AllOrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AllOrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AllOrderDetail record);
}