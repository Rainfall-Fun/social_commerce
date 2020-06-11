package com.cqjtu.membermanageservice.dao;

import com.cqjtu.membermanageservice.db.AllUserInfo;
import com.cqjtu.membermanageservice.db.UserAdress;
import com.cqjtu.membermanageservice.db.UserSearchHistory;
import com.cqjtu.membermanageservice.db.UserSuggest;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManagerUserInfoDao {
    //管理用户信息
    public List<AllUserInfo> SearchUserInfo(Integer user_id);
    public List<AllUserInfo> SearchAllUserInfo(Integer a,Integer b);
    public Integer SearchAlluserCount();
    public void UpdateUserInfo(AllUserInfo allUserInfo);
    public void DeleteUserInfo(Integer user_id);
    //管理用户地址信息
    public List<UserAdress> SearchUserAdressById(Integer user_id,Integer a, Integer b);
    public Integer SearchAdressByIdCount(Integer id);
    public List<AllUserInfo> SearchAllUserAdress(Integer a,Integer b);
    public Integer SearchAlluserCountAdress();
    //管理用户收藏信息
    public List<AllUserInfo> SearchUserCollect(Integer user_id,Integer a, Integer b);
    public Integer SearchUserCollectByIdCount(Integer user_id);
    public List<AllUserInfo> SearchAllUserCollect(Integer a,Integer b);
    public Integer SearchAlluserCountCollect();
    //管理用户意见
    public List<UserSuggest>  SearchSuggest(Integer user_id,Integer a,Integer b );
    public List<AllUserInfo> SearchAllSuggest(Integer a,Integer b);
    public Integer SearchAllSuggestCount();
    public Integer SearchSuggestCountByIdCount(Integer user_id);



    public void Addkeywords(UserSearchHistory userSearchHistory);
    public List<UserSearchHistory> SearchHistoryBYid(Integer user_id,Integer a, Integer b);
    public Integer SearchHistoryBYidCount(Integer id);
    public List<UserSearchHistory> searchAllUserHistory(Integer a,Integer b);
    public Integer SearchAllUserHistoryCount();

}
