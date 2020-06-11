package com.cqjtu.membermanageservice.service;

import com.cqjtu.membermanageservice.dao.OperatorInfoDao;
import com.cqjtu.membermanageservice.dao.UserInfoDao;
import com.cqjtu.membermanageservice.db.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserInfoDao userInfoDao;
    //用户注销自身账号
    public void Logoff(Integer user_id)
    {
        userInfoDao.Logoff(user_id);
    }
    //用户获取自身信息
    public Object AuthInfo(Integer user_id)
    {
       return   userInfoDao.AuthInfo(user_id);
    }
    //用户修改自身用户名信息
    public void SaveName(Integer user_id,String nickName)
    {
        userInfoDao.SaveName(user_id,nickName);
    }
    //用户修改自身性别信息
    public void SaveSex(Integer user_id,Boolean sex)
    {
        userInfoDao.SaveSex(user_id,sex);
    }
    public void Submit(UserSuggest userSuggest)//用户提交意见
    {userInfoDao.Submit(userSuggest);}
    //用户修改自身电话信息
    public void SaveMobile(Integer user_id,String mobile)
    {
        userInfoDao.SaveMobile(user_id,mobile);
    }
    //获取用户收藏信息
    public List<UserCollect> collectList(Integer user_id)
    {
        return userInfoDao.collectList(user_id);
    }
    public Item Convert(Integer valueId)
    {
        return userInfoDao.Convert(valueId);
    }
    //用户删除收藏信息
    public Integer  SearchDeletedInfo(Integer user_id,Integer valueId)
    {
        return userInfoDao.SearchDeletedInfo(user_id,valueId);
    }
     public void DeleteCollect(Integer collect_id)
     {
         userInfoDao.DeleteCollect(collect_id);
     }
     public void UpGrade(Integer user_id,Integer grade,Integer score)
     {
         userInfoDao.UpGrade(user_id,grade,score);
     }




    public  List<UserAdress> AdressList(Integer user_id)
     {return userInfoDao.AdressList(user_id);}

    public  List<UserAdress> detail(Integer id)
    {return userInfoDao.detail(id);}

    public void SaveAdress(UserAdress userAdress)
    {
        userInfoDao.SaveAdress(userAdress);
    }

    public void UpdteUserAdress(UserAdress userAdress){userInfoDao.UpdateUserAdress(userAdress);}

    public void DeleteAdress(UserAdress userAdress)
    {
        userInfoDao.DeleteAdress(userAdress);
    }


}
