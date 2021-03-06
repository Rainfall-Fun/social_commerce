package com.cqjtu.sc.admin.goods.db.dao;

import com.cqjtu.sc.admin.goods.db.domain.AllCart;
import com.cqjtu.sc.admin.goods.db.domain.AllCartExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AllCartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    long countByExample(AllCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    int deleteByExample(AllCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer cartId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    int insert(AllCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    int insertSelective(AllCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    AllCart selectOneByExample(AllCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    AllCart selectOneByExampleSelective(@Param("example") AllCartExample example, @Param("selective") AllCart.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    List<AllCart> selectByExampleSelective(@Param("example") AllCartExample example, @Param("selective") AllCart.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    List<AllCart> selectByExample(AllCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    AllCart selectByPrimaryKeySelective(@Param("cartId") Integer cartId, @Param("selective") AllCart.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    AllCart selectByPrimaryKey(Integer cartId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AllCart record, @Param("example") AllCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AllCart record, @Param("example") AllCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AllCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AllCart record);
}