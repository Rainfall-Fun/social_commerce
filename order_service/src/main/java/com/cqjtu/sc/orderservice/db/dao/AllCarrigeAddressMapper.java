package com.cqjtu.sc.orderservice.db.dao;

import com.cqjtu.sc.orderservice.db.domain.AllCarrigeAddress;
import com.cqjtu.sc.orderservice.db.domain.AllCarrigeAddressExample;
import java.util.List;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Param;

=======

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
>>>>>>> 547cab64eb76ae209f57ad6843f97b3160e0f76a
public interface AllCarrigeAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    long countByExample(AllCarrigeAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    int deleteByExample(AllCarrigeAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer addressId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    int insert(AllCarrigeAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    int insertSelective(AllCarrigeAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    AllCarrigeAddress selectOneByExample(AllCarrigeAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    AllCarrigeAddress selectOneByExampleSelective(@Param("example") AllCarrigeAddressExample example, @Param("selective") AllCarrigeAddress.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    List<AllCarrigeAddress> selectByExampleSelective(@Param("example") AllCarrigeAddressExample example, @Param("selective") AllCarrigeAddress.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    List<AllCarrigeAddress> selectByExample(AllCarrigeAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    AllCarrigeAddress selectByPrimaryKeySelective(@Param("addressId") Integer addressId, @Param("selective") AllCarrigeAddress.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    AllCarrigeAddress selectByPrimaryKey(Integer addressId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AllCarrigeAddress record, @Param("example") AllCarrigeAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AllCarrigeAddress record, @Param("example") AllCarrigeAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AllCarrigeAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_carrige_address
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AllCarrigeAddress record);
}