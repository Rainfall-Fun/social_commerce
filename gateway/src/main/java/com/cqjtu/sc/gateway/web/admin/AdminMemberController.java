package com.cqjtu.sc.gateway.web.admin;

import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.web.service.ManageUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.FormSubmitEvent;
import java.util.Map;

@RestController
public class AdminMemberController {
    @Autowired
    ManageUserService manageUserService;
    //用户注销自身账号
    @GetMapping ("wx/auth/logoff")
    public Object Logoff(@RequestParam Integer user_id)
    {
        manageUserService.Logoff(user_id);
        return ResponseUtil.ok();
    }
    //用户获取自身信息
    @GetMapping("wx/auth/info")
    public Object AuthInfo(@RequestParam Integer user_id)
    {
        Object o =  manageUserService.AuthInfo(user_id);
        return ResponseUtil.ok(o);
    }
    //用户修改自身用户名信息
    @GetMapping("wx/auth/savename")
    public Object SaveName(
                           @RequestParam Integer user_id,
                           @RequestParam String nickName
                           )
    {
        manageUserService.SaveName(user_id,nickName);
        return   ResponseUtil.ok();
    }
    //用户修改自身性别信息
    @PostMapping("wx/auth/saveSex")
    public Object SaveSex(@RequestBody Map map)
    {
        manageUserService.SaveSex(map);
        return  ResponseUtil.ok();
    }
    //用户修改自身电话信息
    @PostMapping("wx/auth/saveMoblie")
    public Object SaveMobile(@RequestBody Map map)
    {
        manageUserService.SaveMobile(map);
        return  ResponseUtil.ok();
    }
    //获取用户收藏信息
    @RequestMapping("wx/collect/list")
    public Object collectList (@RequestBody Map map)
    {
        Object o = manageUserService.collectList(map);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }
    //用户删除收藏信息
    @RequestMapping("wx/collect/addordelete")
    public Object CollectAddOrDelete (@RequestBody Map map)
    {
        manageUserService.CollectAddOrDelete(map);
        return ResponseUtil.ok();
    }












    @GetMapping("/admin/user/SearchUserInfo")
    public Object SearchByUserid(@RequestParam Integer user_id) {
        Object o = manageUserService.SearchByUserid(user_id);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }

    @GetMapping("/admin/user/SearchAllUserInfo")
    public Object SearchAllUserInfo(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order) {
        Object o = manageUserService.SearchAllUserInfo(page, limit, sort, order);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }

    @GetMapping("/admin/user/UpdateUserInfo")
    public void UpdateUserInfo(@RequestParam Integer userId,
                               @RequestParam String username,
                               @RequestParam String phoneNumber,
                               @RequestParam Boolean userSexy,
                               @RequestParam Integer invitor,
                               @RequestParam String bankNumber,
                               @RequestParam Boolean isValid,
                               @RequestParam Integer grade,
                               @RequestParam Integer score) {
        manageUserService.UpdateUserInfo(userId, username, phoneNumber, userSexy, invitor, bankNumber, isValid, grade, score);
        System.out.println(userId);
    }

    @GetMapping("/admin/user/DeleteUserInfo")
    public void DeleteteUserInfo(@RequestParam Integer userId,
                                 @RequestParam String username,
                                 @RequestParam String phoneNumber,
                                 @RequestParam Boolean userSexy,
                                 @RequestParam Integer invitor,
                                 @RequestParam String bankNumber,
                                 @RequestParam Boolean isValid,
                                 @RequestParam Integer grade,
                                 @RequestParam Integer score) {
        manageUserService.DeleteteUserInfo(userId, username, phoneNumber, userSexy, invitor, bankNumber, isValid, grade, score);
    }


    @GetMapping("/admin/user/SearchUserAdressById")
    public Object SearchUserAdressById(@RequestParam Integer user_id,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit,
                                       @RequestParam(defaultValue = "qqsj") String sort,
                                       @RequestParam(defaultValue = "desc") String order
    ) {
        Object o = manageUserService.SearchUserAdressById(user_id, page, limit, sort, order);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }

    @GetMapping("/admin/user/SearchAllUserAdress")
    public Object SearchAllUserAdress(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam(defaultValue = "qqsj") String sort,
                                      @RequestParam(defaultValue = "desc") String order) {
        Object o = manageUserService.SearchAllUserAdress(page, limit, sort, order);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }


    @GetMapping("/admin/user/SearchUserCollect")
    public Object SearchUserCollect(@RequestParam Integer userId,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit,
                                    @RequestParam(defaultValue = "qqsj") String sort,
                                    @RequestParam(defaultValue = "desc") String order) {
        Object o = manageUserService.SearchUserCollect(userId,page,limit,sort,order);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }

    @GetMapping("/admin/user/SearchAllUserCollect")
    public Object SearchAllUserCollect(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order) {
        Object o = manageUserService.SearchAllUserCollect(page, limit, sort, order);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }


    @RequestMapping("wx/feedback/submit")//用户提交意见
    public Object Submit(@RequestBody Map map) {

        manageUserService.Submit(map.get("feedType").toString(),
                map.get("content").toString(),
                Integer.valueOf(map.get("user_id").toString()),
                map.get("value").toString());
        System.out.println(map.get("value").toString());
        return ResponseUtil.ok();
    }

    @RequestMapping("/admin/user/SearchSuggest")
    public Object SearchSuggest(@RequestParam Integer id,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer limit,
                                @RequestParam(defaultValue = "qqsj") String sort,
                                @RequestParam(defaultValue = "desc") String order) {
        Object o = manageUserService.SearchSuggest(id, page, limit, sort, order);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }

    @GetMapping("/admin/user/SearchAllSuggest")
    public Object SearchAllSuggest(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order) {
        Object o = manageUserService.SearchAllSuggest(page, limit, sort, order);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }

    @GetMapping("wx/address/AdressList")
    public Object AdressList(@RequestParam Integer user_id) {
        Object o = manageUserService.AdressList(user_id);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }

    @RequestMapping("wx/address/detail")
    public Object detail(@RequestParam Integer id) {
        Object o = manageUserService.detail(id);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }

    @PostMapping("wx/address/save")
    public Object SaveAdress(@RequestBody Map map) {
        manageUserService.Saveadress(map);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return ResponseUtil.ok();
    }

    @PostMapping("wx/address/delete")
    public Object DeleteAdress(@RequestBody Map map) {
        manageUserService.DeleteAdress(map);
        return ResponseUtil.ok();
    }


    @PostMapping("wx/adress/addkeywords")
    public void Addkeywords(@RequestBody Map map) {
        manageUserService.Addkeywords(map);
    }

    @RequestMapping("/admin/user/searchHistory")
    public Object searchHistoryBYid(@RequestParam Integer userId,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit,
                                    @RequestParam(defaultValue = "qqsj") String sort,
                                    @RequestParam(defaultValue = "desc") String order
    ) {

        Object o = manageUserService.searchHistoryBYid(userId, page, limit, sort, order);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }

    @RequestMapping("/admin/user/searchAllUserHistory")
    public Object searchAllUserHistory(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "qqsj") String sort,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Object o = manageUserService.searchAllUserHistory(page, limit, sort, order);
        System.out.println(o);
        return ResponseUtil.ok(o);
    }
    @RequestMapping("wx/collect/upgrade")
    public Object UpGrade(@RequestParam Integer user_id,
                          @RequestParam Integer grade,
                          @RequestParam Integer score)
    {
        System.out.println(user_id);
        System.out.println(grade);
        System.out.println(score);
        manageUserService.UpGrade(user_id,grade,score);
        return ResponseUtil.ok();
    }



}




