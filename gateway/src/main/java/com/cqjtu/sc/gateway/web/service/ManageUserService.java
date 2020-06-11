package com.cqjtu.sc.gateway.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "member-manage-service")
public interface ManageUserService {
    //用户注销自身账号
    @GetMapping("logoff")
    void  Logoff(@RequestParam(value = "user_id") Integer user_id);
    //用户获取自身信息
    @GetMapping("info")
    Object AuthInfo(@RequestParam(value = "user_id")Integer user_id);
    //用户修改自身用户名信息
    @GetMapping("savename")
    void SaveName(@RequestParam Integer user_id,
                  @RequestParam String nickName);
    //用户修改自身性别信息
    @PostMapping("saveSex")
    void SaveSex(@RequestBody Map map);
    //用户修改自身电话信息
    @PostMapping("saveMoblie")
    void SaveMobile(@RequestBody Map map);
    //获取用户收藏信息
    @PostMapping("list")
    Object collectList(@RequestBody Map map);
    //用户删除收藏信息
    @RequestMapping("addordelete")
    void CollectAddOrDelete (@RequestBody  Map map);



     @GetMapping("SearchUserInfo")
    Object SearchByUserid(@RequestParam(value = "user_id") Integer user_id);

    @GetMapping("SearchAllUserInfo")
    Object SearchAllUserInfo(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order);

    @GetMapping("UpdateUserInfo")
    void UpdateUserInfo(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "phoneNumber") String phoneNumber,
            @RequestParam(value = "userSexy") Boolean userSexy,
            @RequestParam(value = "invitor") Integer invitor,
            @RequestParam(value = "bankNumber") String bankNumber,
            @RequestParam(value = "isValid") Boolean isValid,
            @RequestParam(value = "grade") Integer grade,
            @RequestParam(value = "score") Integer score);

    @GetMapping("DeleteUserInfo")
    void DeleteteUserInfo(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "phoneNumber") String phoneNumber,
            @RequestParam(value = "userSexy") Boolean userSexy,
            @RequestParam(value = "invitor") Integer invitor,
            @RequestParam(value = "bankNumber") String bankNumber,
            @RequestParam(value = "isValid") Boolean isValid,
            @RequestParam(value = "grade") Integer grade,
            @RequestParam(value = "score") Integer score);





    @GetMapping("SearchUserAdressById")
    Object SearchUserAdressById(
            @RequestParam(value = "user_id") Integer user_id,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order) ;

    @GetMapping("SearchAllUserAdress")
    Object SearchAllUserAdress(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order);






    @GetMapping("SearchUserCollect")
    Object SearchUserCollect(@RequestParam Integer userId,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(defaultValue = "qqsj") String sort,
                             @RequestParam(defaultValue = "desc") String order);

    @GetMapping("SearchAllUserCollect")
    Object SearchAllUserCollect(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order);


    @PostMapping("submit")
    void Submit(@RequestParam String feedType, @RequestParam String content, @RequestParam Integer user_id, @RequestParam String value);

    @GetMapping("SearchSuggest")
    Object SearchSuggest(@RequestParam(value = "id") Integer id,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer limit,
                         @RequestParam(defaultValue = "qqsj") String sort,
                         @RequestParam(defaultValue = "desc") String order);

    @GetMapping("SearchAllSuggest")
    Object SearchAllSuggest(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order);


    @GetMapping("AdressList")
    public Object AdressList(@RequestParam Integer user_id);

    @GetMapping("detail")
    public Object detail(@RequestParam Integer id);

    @PostMapping("save")
    public Object Saveadress(@RequestBody Map mp);

    @PostMapping("delete")
    public Object DeleteAdress(@RequestBody Map mp);


     @PostMapping("addkeywords")
    public void Addkeywords(@RequestBody Map map);


    @GetMapping("searchHistory")
    Object searchHistoryBYid(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order) ;
    @GetMapping("searchAllUserHistory")
    Object searchAllUserHistory(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order) ;

    @GetMapping("upgrade")
    Object UpGrade(@RequestParam Integer user_id,
                   @RequestParam Integer grade,
                   @RequestParam Integer score);

}
