package com.cqjtu.sc.gateway.web.wx;

import com.cqjtu.sc.gateway.dao.entity.wx.UserInfo;
import com.cqjtu.sc.gateway.dao.entity.wx.UserWxInfo;
import com.cqjtu.sc.gateway.dao.service.UserInfoService;
import com.cqjtu.sc.gateway.dao.service.UserWxInfoService;
import com.cqjtu.sc.gateway.service.UserTokenManager;
import com.cqjtu.sc.gateway.web.service.WXService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginRegisterController {
    @Autowired
    UserWxInfoService userWxInfoService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    WXService wxService;
    @RequestMapping("/wx/auth/login_by_weixin")
    public Object loginByWx(String code){
        String openid = wxService.getOpenid(code);
        Integer userInfoId;
        String userName;
        if (!StringUtils.isBlank(openid)){
            //根据openid判断是否已注册
            UserWxInfo userWxInfoByOpenid = userWxInfoService.getUserWxInfoByOpenid(openid);
            if (userWxInfoByOpenid==null){//需要注册，之后登录
                userInfoId = userInfoService.add(null);
                UserInfo byUserInfoId = userInfoService.getByUserInfoId(userInfoId);
                byUserInfoId.setMemberNumber("8888"+userInfoId);//会员号
                userInfoService.update(byUserInfoId);
                userWxInfoService.add(userInfoId,openid);
                userName="8888"+userInfoId;
            }else {//直接登录
                UserWxInfo userWxInfoByOpenid1 = userWxInfoService.getUserWxInfoByOpenid(openid);
                userInfoId=userWxInfoByOpenid1.getUserInfoId();
                UserInfo byUserInfoId = userInfoService.getByUserInfoId(userInfoId);
                userName=byUserInfoId.getMemberNumber();
            }
            HashMap<Object, Object> map = new HashMap<>();
            map.put("token",UserTokenManager.generateToken(userInfoId));
            map.put("userName",userName);
            return ok(map);
        }
        return fail(500,"登录失败");
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        obj.put("data", data);
        return obj;
    }

    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }

}
