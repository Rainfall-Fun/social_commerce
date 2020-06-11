package com.cqjtu.membermanageservice.dao;

import com.cqjtu.membermanageservice.db.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInfoDao {
    //用户注销自身账号
    public void Logoff(Integer user_id);
   //用户获取自身信息
    public Object AuthInfo(Integer user_id);
    //用户修改自身用户名信息
    public  void SaveName(Integer user_id,String nickName);
    //用户修改自身性别信息
    public  void SaveSex(Integer user_id,Boolean sex);
   //用户修改自身电话信息
    public  void SaveMobile(Integer user_id,String mobile);
    //获取用户收藏信息
    public List<UserCollect> collectList(Integer user_id);
    public Item  Convert(Integer valueId);
    //用户删除收藏信息
    public Integer SearchDeletedInfo(Integer user_id,Integer valueId);
    public void DeleteCollect(Integer collect_id);



    public void Submit(UserSuggest userSuggest);//用户提交意见
    public  List<UserAdress> AdressList(Integer user_id);
    public  List<UserAdress> detail(Integer adressid);
    public  void SaveAdress(UserAdress userAdress);
    public void UpdateUserAdress(UserAdress userAdress);
    public  void DeleteAdress(UserAdress userAdress);
    public void UpGrade(Integer user_id,Integer grade,Integer score);
}
