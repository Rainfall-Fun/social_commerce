package com.cqjtu.sc.gateway.web.wx;

import com.cqjtu.sc.gateway.dao.service.UserInfoService;
import com.cqjtu.sc.gateway.dao.service.UserWxInfoService;
import com.cqjtu.sc.gateway.service.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginRegisterController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserWxInfoService userWxInfoService;
    @Autowired
    UserInfoService userInfoService;
    @RequestMapping("/wx/auth/login_by_weixin")
    public Object loginByWx(String code){
        String openid = restTemplate.getForObject("http://WX-SERVICE/getOpenid/" + code, String.class);

        Integer userInfoId;
        long userName;
        String s = UserTokenManager.generateToken(1);
        System.out.println(s);
//        if (!StringUtils.isBlank(openid)){
//            //根据openid判断是否已注册
//            UserWxInfo userWxInfoByOpenid = userWxInfoService.getUserWxInfoByOpenid(openid);
//            if (userWxInfoByOpenid==null){//需要注册，之后登录
//                userInfoId = userInfoService.add(Long.valueOf(19971024), null);
//                userWxInfoService.add(userInfoId,openid,new Date());//该用户在user_wx_info表中的主键
//                userName=19971024;
//            }else {//直接登录
//                UserWxInfo userWxInfoByOpenid1 = userWxInfoService.getUserWxInfoByOpenid(openid);
//                userInfoId=userWxInfoByOpenid1.getUserInfoId();
//                userName=19971024;
//            }
//            HashMap<Object, Object> map = new HashMap<>();
//            map.put("token",UserTokenManager.generateToken(userInfoId));
//            map.put("userName",userName);
//            return ok(map);
//        }
        return ok("1");
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        obj.put("data", data);
        return obj;
    }

}
