package com.cqjtu.membermanageservice.controller;

import com.cqjtu.membermanageservice.db.AllUserInfo;
import com.cqjtu.membermanageservice.db.UserSearchHistory;
import com.cqjtu.membermanageservice.service.ManageUserService;
import com.cqjtu.membermanageservice.service.UserService;
import com.cqjtu.membermanageservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@Validated
public class ManageUserInfo {

    @Autowired
    private ManageUserService manageUserService;
    AllUserInfo alluserinfo = new AllUserInfo();

    //用户信息管理
    @GetMapping("/SearchUserInfo")
    public Object SearchUserInfo(@RequestParam("user_id") Integer user_id)
    {
        return ResponseUtil.okList(manageUserService.SearchUserInfo(user_id));
    }
    @GetMapping("/SearchAllUserInfo")
    public Object SearchAllUserInfo(
                                     @RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit,
                                     @RequestParam(defaultValue = "qqsj") String sort,
                                     @RequestParam(defaultValue = "desc") String order)
    {
        Integer allusercount=SearchAlluserCount();

        return ResponseUtil.okListToAll(manageUserService.SearchAllUserInfo(page,limit),allusercount);
    }

    public Integer SearchAlluserCount()
    {
       return   manageUserService.SearchAlluserCount();
    }
    @GetMapping("/UpdateUserInfo")
    public void UpdateUserInfo(@RequestParam("userId") Integer user_id,
                                 @RequestParam("username") String user_name,
                                 @RequestParam("phoneNumber") String phonenumber,
                                 @RequestParam("userSexy") Boolean user_sexy,
                                 @RequestParam("invitor") Integer invitor,
                                 @RequestParam("bankNumber") String bank_number,
                                 @RequestParam("isValid") Boolean is_valid,
                                 @RequestParam("grade") Integer grade,
                                 @RequestParam("score") Integer score) {
            alluserinfo.userId = user_id;
            alluserinfo.username = user_name;
            alluserinfo.phoneNumber= phonenumber;
            alluserinfo.userSexy = user_sexy;
            alluserinfo.invitor = invitor;
            alluserinfo.bankNumber= bank_number;
            alluserinfo.isValid = is_valid;
            alluserinfo.grade = grade;
            alluserinfo.score = score;
           manageUserService.UpdateUserInfo(alluserinfo);
           System.out.println(SearchUserInfo(user_id));
    }
    @GetMapping("/DeleteUserInfo")
    public void DeleteUserInfo(@RequestParam("userId") Integer user_id,
                               @RequestParam("username") String user_name,
                               @RequestParam("phoneNumber") String phonenumber,
                               @RequestParam("userSexy") Boolean user_sexy,
                               @RequestParam("invitor") Integer invitor,
                               @RequestParam("bankNumber") String bank_number,
                               @RequestParam("isValid") Boolean is_valid,
                               @RequestParam("grade") Integer grade,
                               @RequestParam("score") Integer score) {
        manageUserService.DeleteUserInfo(user_id);
        System.out.println(user_id);
    }














    //用户地址管理
    @GetMapping("/SearchUserAdressById")
    public Object SearchUserAdressById(@RequestParam("user_id") Integer user_id ,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit,
                                       @RequestParam(defaultValue = "qqsj") String sort,
                                       @RequestParam(defaultValue = "desc") String order)
    {
        Integer searvchUserAdressByIdCount = SearchAdressByIdCount(user_id);
        return ResponseUtil.okListToAll(manageUserService.SearchUserAdressById(user_id,page,limit),searvchUserAdressByIdCount);
    }

    public Integer SearchAdressByIdCount(Integer user_id)
    {
        return   manageUserService.SearchAdressByIdCount(user_id);
    }

    @GetMapping("/SearchAllUserAdress")
    public Object SearchAllUserAdress(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order)
    {
        Integer allusercount2=SearchAlluserCountAdress();
        return ResponseUtil.okListToAll(manageUserService.SearchAllUserAdress(page,limit),allusercount2);
    }

    public Integer SearchAlluserCountAdress()
    {
        return   manageUserService.SearchAlluserCountAdress();
    }













    @GetMapping("/SearchUserCollect")
    public Object SearchUserCollect(@RequestParam Integer userId,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit,
                                    @RequestParam(defaultValue = "qqsj") String sort,
                                    @RequestParam(defaultValue = "desc") String order)
    {
        Integer searvchUserCollectByIdCount = SearchUserCollectByIdCount(userId);
        return ResponseUtil.okListToAll(manageUserService.SearchUserCollect(userId,page,limit),searvchUserCollectByIdCount);
    }
    public Integer SearchUserCollectByIdCount(Integer user_id)
    {
        return   manageUserService.SearchUserCollectByIdCount(user_id);
    }




    @GetMapping("/SearchAllUserCollect")
    public Object SearchAllUserCollect(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order)
    {
        Integer allusercount3=SearchAlluserCountCollect();
        return ResponseUtil.okListToAll(manageUserService.SearchAllUserCollect(page,limit),allusercount3);
    }
    public Integer SearchAlluserCountCollect()
    {
        return   manageUserService.SearchAlluserCountCollect();
    }

    @GetMapping("/SearchSuggest")//管理员查看用户意见
    public Object SearchSuggest(@RequestParam("id") Integer id,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer limit,
                                @RequestParam(defaultValue = "qqsj") String sort,
                                @RequestParam(defaultValue = "desc") String order)
    {
        Integer userbyidcount = SearchSuggestCountByIdCount(id);
       return ResponseUtil.okListToAll(manageUserService.SearchSuggest(id,page,limit),userbyidcount);
    }
    public Integer SearchSuggestCountByIdCount(Integer user_id)
    {
        return   manageUserService.SearchSuggestCountByIdCount(user_id);
    }

    @GetMapping("/SearchAllSuggest")
    public Object  SearchAllSuggest(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order)
    {
        Integer allusercount= SearchAllSuggestCount();
        return ResponseUtil.okListToAll(manageUserService.SearchAllSuggest(page,limit),allusercount);
    }
    public Integer SearchAllSuggestCount()
    {
        return   manageUserService.SearchAllSuggestCount();
    }









    @PostMapping ("/addkeywords")
    public void Addkeywords(@RequestBody Map map)
    {

        UserSearchHistory userSearchHistory= new UserSearchHistory();
        userSearchHistory.keyWords=map.get("keyword").toString();
        userSearchHistory.userId=Integer.valueOf(map.get("user_id").toString());
        userSearchHistory.addTime= new Date().toString();
        manageUserService.Addkeywords(userSearchHistory);
    }

    @GetMapping("/searchHistory")
    public Object searchHistoryBYid(@RequestParam Integer userId ,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit,
                                       @RequestParam(defaultValue = "qqsj") String sort,
                                       @RequestParam(defaultValue = "desc") String order)
    {
        Integer searchHistoryBYidCount = SearchHistoryBYidCount(userId);
        return ResponseUtil.okListToAll(manageUserService.SearchHistoryBYid(userId,page,limit),searchHistoryBYidCount);
    }

    public Integer SearchHistoryBYidCount(Integer user_id)
    {
        return   manageUserService.SearchHistoryBYidCount(user_id);
    }
    @GetMapping("/searchAllUserHistory")
    public Object  searchAllUserHistory(
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit,
                                    @RequestParam(defaultValue = "qqsj") String sort,
                                    @RequestParam(defaultValue = "desc") String order)
    {
        Integer searchAllUserHistoryCount = SearchAllUserHistoryCount();
        return ResponseUtil.okListToAll(manageUserService.searchAllUserHistory(page,limit),searchAllUserHistoryCount);
    }
    public Integer SearchAllUserHistoryCount()
    {
        return   manageUserService.SearchAllUserHistoryCount();
    }
}

