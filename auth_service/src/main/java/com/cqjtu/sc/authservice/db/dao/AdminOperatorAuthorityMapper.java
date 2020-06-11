package com.cqjtu.sc.authservice.db.dao;

import com.cqjtu.sc.authservice.db.domain.AdminOperatorAuthority;
import com.cqjtu.sc.authservice.db.domain.AdminOperatorAuthorityExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AdminOperatorAuthorityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    long countByExample(AdminOperatorAuthorityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    int deleteByExample(AdminOperatorAuthorityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer operatorAuthId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    int insert(AdminOperatorAuthority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    int insertSelective(AdminOperatorAuthority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    AdminOperatorAuthority selectOneByExample(AdminOperatorAuthorityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    AdminOperatorAuthority selectOneByExampleSelective(@Param("example") AdminOperatorAuthorityExample example, @Param("selective") AdminOperatorAuthority.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    List<AdminOperatorAuthority> selectByExampleSelective(@Param("example") AdminOperatorAuthorityExample example, @Param("selective") AdminOperatorAuthority.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    List<AdminOperatorAuthority> selectByExample(AdminOperatorAuthorityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    AdminOperatorAuthority selectByPrimaryKeySelective(@Param("operatorAuthId") Integer operatorAuthId, @Param("selective") AdminOperatorAuthority.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    AdminOperatorAuthority selectByPrimaryKey(Integer operatorAuthId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AdminOperatorAuthority record, @Param("example") AdminOperatorAuthorityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AdminOperatorAuthority record, @Param("example") AdminOperatorAuthorityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdminOperatorAuthority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_operator_authority
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdminOperatorAuthority record);

    void batchInsert(List<AdminOperatorAuthority> adminActorAuthorities);
}