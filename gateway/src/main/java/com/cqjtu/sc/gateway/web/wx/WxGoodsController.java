package com.cqjtu.sc.gateway.web.wx;

import com.cqjtu.sc.gateway.annotation.LoginUser;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx/goods/")
public class WxGoodsController {
    @RequestMapping("list")
    public Object test(@LoginUser Integer userInfoId){
        System.out.println("id "+userInfoId);
        if (userInfoId==null){
            return unlogin();
        }
        return "{1111}";
    }
    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }



    public static Object unlogin() {
        return fail(501, "请登录");
    }

}
