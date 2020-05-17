package com.cqjtu.sc.wx.api;

import com.cqjtu.sc.wx.properties.WxProperties;
import com.cqjtu.sc.wx.util.HttpUtil;
import com.cqjtu.sc.wx.util.JacksonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OpenidApi {
    @Autowired
    WxProperties wxProperties;
    @RequestMapping(value = "/getOpenid",method = RequestMethod.POST)
    public String getOpenid(@RequestParam("code") String code){
        System.out.println(code);
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ wxProperties.appID +"&secret="+wxProperties.appsecret+"&code="+code+"&grant_type=authorization_code";
        try {
            String result = HttpUtil.doGet(url);
            if (!StringUtils.isBlank(result)){
                return JacksonUtil.parseString(result,"openid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
