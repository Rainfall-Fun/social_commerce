package com.cqjtu.membermanageservice.controller;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.cqjtu.membermanageservice.db.*;
import com.cqjtu.membermanageservice.service.OperatorService;
import com.cqjtu.membermanageservice.service.UserService;
import com.cqjtu.membermanageservice.util.ResponseUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class UserInfo {
    @Autowired
    private UserService userService;
    //用户注销自身账号
    @GetMapping("/logoff")
    public void Logoff(@RequestParam Integer user_id)
    {
        userService.Logoff(user_id);
    }
    //用户获取自身信息
    @GetMapping("/info")
    public Object AuthInfo(@RequestParam Integer user_id)
    {
       return   userService.AuthInfo(user_id);
    }
    //用户修改自身用户名信息
    @GetMapping("/savename")
    public void SaveName(@RequestParam Integer user_id,
                         @RequestParam String nickName)
    {
        userService.SaveName(user_id,nickName);
    }
    //用户修改自身性别信息
    @PostMapping("/saveSex")
    public void SaveSex(@RequestBody Map map)
    {
        Integer user_id = Integer.valueOf(map.get("user_id").toString());
        Boolean sex=false;
        System.out.println(map.get("gender").toString());
                if(map.get("gender").toString().equals("[男]"))
                {
                    sex=true;
                }
                else
                {
                    sex=false;
                }
        System.out.println(user_id);
        System.out.println(sex);
        userService.SaveSex(user_id,sex);
    }
    //用户修改自身电话信息
    @PostMapping("/saveMoblie")
    public void SaveMobile(@RequestBody Map map)
    {
       Integer user_id=Integer.valueOf(map.get("user_id").toString());
       String mobile=map.get("mobile").toString();
       userService.SaveMobile(user_id,mobile);
    }
    //获取用户收藏信息
    @PostMapping("/list")
    public Object collectList(@RequestBody Map map)
    {
        Integer user_id=Integer.valueOf(map.get("user_id").toString());
        List<Item> itemlist= new ArrayList<>();
        List<UserCollect>  list = userService.collectList(user_id);
        for (int j=0;j<list.size();j++)
        {
            itemlist.add(userService.Convert(Integer.valueOf(list.get(j).valueId)));
        }
        return ResponseUtil.okList(itemlist);
    }
    //用户删除收藏信息
    @PostMapping("/addordelete")
    public void CollectAddOrDelete(@RequestBody Map map)
    {
        Integer user_id=Integer.valueOf(map.get("user_id").toString());
        Integer valueId=Integer.valueOf(map.get("valueId").toString());
        System.out.println(user_id);
        System.out.println((valueId));
        Integer collect_id  = userService.SearchDeletedInfo(user_id,valueId);
        userService.DeleteCollect(collect_id);
    }
    @GetMapping("/upgrade")
    public void  UpGrade(@RequestParam Integer user_id,
                         @RequestParam Integer grade,
                         @RequestParam Integer score)
    {
          grade+=1;
          score-=100;
         userService.UpGrade(user_id,grade,score);

    }




















    @PostMapping("/submit")
    public void Submit(@RequestParam String feedType,@RequestParam String content,@RequestParam Integer user_id,@RequestParam String value)
    {
        UserSuggest userSuggest=new UserSuggest();
        userSuggest.userId=user_id;
        userSuggest.suggestionType=feedType;
        userSuggest.Suggestion=content;
        userSuggest.time=value;
        System.out.println(userSuggest.userId);
        System.out.println(userSuggest.suggestionType);
        System.out.println(userSuggest.Suggestion);
        System.out.println(userSuggest.time);
        userService.Submit(userSuggest);
    }

    @GetMapping("/AdressList")
    public Object AdressList(@RequestParam("user_id") Integer user_id)
    {
        return ResponseUtil.okList(userService.AdressList(user_id));
    }
    @GetMapping("/detail")
    public Object detail(@RequestParam Integer id)
    {
        return ResponseUtil.okList(userService.detail(id));
    }

    @PostMapping("/save")
    public Object SaveAdress(@RequestBody Map map)
    {
        UserAdress userAdress = new UserAdress();

        userAdress.userId=Integer.valueOf(map.get("user_id").toString());
         userAdress.username= map.get("name").toString();
         userAdress.tel=map.get("tel").toString();
         userAdress.province=map.get("province").toString();
         userAdress.city=map.get("city").toString();
         userAdress.country=map.get("county").toString();
        userAdress.areaCode=map.get("areaCode").toString();
        userAdress.adress=map.get("addressDetail").toString();
         if(map.get("isDefault")==null)
         {
             userAdress.isDefault=false;
         }
         else
         {
             userAdress.isDefault=Boolean.valueOf(map.get("isDefault").toString());
         }
        userAdress.adressId=Integer.valueOf(map.get("addressId").toString());
        //判断新插入还是更新
         if(userAdress.adressId==-1)
         {
             System.out.println("空，插入");
             if (userAdress.isDefault==false)
             {
                 System.out.println("非默认地址，直接插入");
                 userService.SaveAdress(userAdress);
             }
             else
             {
                 System.out.println("默认地址，插入前将其他地址默认地址属性置为false");
                 List<UserAdress> list = new ArrayList<>();
                 list=userService.AdressList(userAdress.userId);
                 for(int i=0;i<list.size();i++)
                 {
                     if (list.get(i).isDefault==true)
                     {
                         list.get(i).isDefault=false;
                         userService.UpdteUserAdress(list.get(i));
                     }
                 }
                 userService.SaveAdress(userAdress);
             }
         }
         else
         {
             System.out.println("非空，更新");
             if (userAdress.isDefault==false)
             {
                 System.out.println("非默认地址，直接修改");
                 userAdress.adressId=Integer.valueOf(map.get("addressId").toString());
                 System.out.println(userAdress.isDefault);
                 userService.UpdteUserAdress(userAdress);
             }
             else
             {
                 List<UserAdress> list = new ArrayList<>();
                 list=userService.AdressList(userAdress.userId);
                 for(int i=0;i<list.size();i++)
                 {
                     if (list.get(i).isDefault==true)
                     {
                         list.get(i).isDefault=false;
                        userService.UpdteUserAdress(list.get(i));
                         userAdress.adressId=Integer.valueOf(map.get("addressId").toString());
                        userService.UpdteUserAdress(userAdress);
                     }
                     else
                     {
                         userAdress.adressId=Integer.valueOf(map.get("addressId").toString());
                         userService.UpdteUserAdress(userAdress);
                     }
                 }
             }
         }
     return ResponseUtil.ok();
    }
    @PostMapping("/delete")
    public Object DeleteAdress(@RequestBody Map map)
    {
        UserAdress userAdress = new UserAdress();
         userAdress.adressId=Integer.valueOf(map.get("addressId").toString());
        userService.DeleteAdress(userAdress);
        return ResponseUtil.ok();
    }

}

