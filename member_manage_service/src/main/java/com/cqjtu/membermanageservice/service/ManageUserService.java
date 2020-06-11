package com.cqjtu.membermanageservice.service;

import com.cqjtu.membermanageservice.dao.ManagerUserInfoDao;
import com.cqjtu.membermanageservice.db.AllUserInfo;
import com.cqjtu.membermanageservice.db.UserAdress;
import com.cqjtu.membermanageservice.db.UserSearchHistory;
import com.cqjtu.membermanageservice.db.UserSuggest;
import net.sf.jsqlparser.statement.select.Offset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageUserService {

    @Autowired
    private ManagerUserInfoDao managerUserInfoDao;
    public List<AllUserInfo> SearchUserInfo(Integer user_id)
    {
        return managerUserInfoDao.SearchUserInfo(user_id);
    }
    public List<AllUserInfo> SearchAllUserInfo(Integer page,Integer limit)
    {
        Integer a = (page-1)*limit;
        Integer b = limit;
        return managerUserInfoDao.SearchAllUserInfo(a,b);
    }
    public Integer SearchAlluserCount()
    {
        return   managerUserInfoDao.SearchAlluserCount();
    }
    public void  UpdateUserInfo(AllUserInfo allUserInfo)
    {
        managerUserInfoDao.UpdateUserInfo(allUserInfo);
    }
    public void DeleteUserInfo(Integer user_id) {managerUserInfoDao.DeleteUserInfo(user_id);}





    public List<UserAdress> SearchUserAdressById(Integer user_id,Integer page,Integer limit)
    {
        Integer a = (page-1)*limit;
        Integer b = limit;
        return managerUserInfoDao.SearchUserAdressById(user_id,a,b);
    }

    public  Integer SearchAdressByIdCount(Integer id)
    {
        return   managerUserInfoDao.SearchAdressByIdCount(id);
    }

    public List<AllUserInfo> SearchAllUserAdress(Integer page,Integer limit)
    {
        Integer a = (page-1)*limit;
        Integer b = limit;
        return managerUserInfoDao.SearchAllUserAdress(a,b);
    }

    public Integer SearchAlluserCountAdress()
    {
        return   managerUserInfoDao.SearchAlluserCountAdress();
    }





    public  Integer SearchUserCollectByIdCount(Integer id)
    {
        return   managerUserInfoDao.SearchUserCollectByIdCount(id);
    }

    public List<AllUserInfo> SearchUserCollect(Integer user_id,Integer page,Integer limit)
    {
        Integer a = (page-1)*limit;
        Integer b = limit;
        return managerUserInfoDao.SearchUserCollect(user_id,a,b);
    }
    public List<AllUserInfo> SearchAllUserCollect(Integer page,Integer limit)
    {
        Integer a = (page-1)*limit;
        Integer b = limit;
        return managerUserInfoDao.SearchAllUserCollect(a,b);
    }
    public Integer SearchAlluserCountCollect()
    {
        return   managerUserInfoDao.SearchAlluserCountCollect();
    }



    //管理员查看用户意见
    public List<UserSuggest> SearchSuggest(Integer user_id,Integer page,Integer limit)
    {
        Integer a = (page-1)*limit;
        Integer b = limit;
        return managerUserInfoDao.SearchSuggest(user_id,a,b);
    }

    public Integer SearchSuggestCountByIdCount(Integer user_id)
    {
        return   managerUserInfoDao.SearchSuggestCountByIdCount(user_id);
    }


    public List<AllUserInfo> SearchAllSuggest(Integer page,Integer limit)
    {
        Integer a = (page-1)*limit;
        Integer b = limit;
        return managerUserInfoDao.SearchAllSuggest(a,b);
    }
    public Integer SearchAllSuggestCount()
    {
        return   managerUserInfoDao.SearchAllSuggestCount();
    }











    public void Addkeywords(UserSearchHistory userSearchHistory){ managerUserInfoDao.Addkeywords(userSearchHistory);}

    public List<UserSearchHistory> SearchHistoryBYid(Integer user_id,Integer page,Integer limit)
    {
        Integer a = (page-1)*limit;
        Integer b = limit;
        return managerUserInfoDao.SearchHistoryBYid(user_id,a,b);
    }

    public  Integer SearchHistoryBYidCount(Integer id)
    {
        return   managerUserInfoDao.SearchHistoryBYidCount(id);
    }

    public List<UserSearchHistory> searchAllUserHistory(Integer page,Integer limit)
    {
        Integer a = (page-1)*limit;
        Integer b = limit;
        return managerUserInfoDao.searchAllUserHistory(a,b);
    }
    public Integer SearchAllUserHistoryCount()
    {
        return   managerUserInfoDao.SearchAllUserHistoryCount();
    }

}
