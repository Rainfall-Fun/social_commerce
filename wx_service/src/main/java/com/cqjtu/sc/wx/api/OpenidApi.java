package com.cqjtu.sc.wx.api;

import com.cqjtu.sc.wx.properties.WxProperties;
import com.cqjtu.sc.wx.util.HttpUtil;
import com.cqjtu.sc.wx.util.JacksonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenidApi {
    @Autowired
    WxProperties wxProperties;
    @RequestMapping(value = "/getOpenid/{code}",method = RequestMethod.GET)
    public String getOpenid(@PathVariable("code") String code){
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ wxProperties.appID +"&secret="+wxProperties.appsecret+"&code="+code+"&grant_type=authorization_code";
        try {
            String result = HttpUtil.doGet(url);
            System.out.println(result);
            if (!StringUtils.isBlank(result)){
                return JacksonUtil.parseString(result,"openid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
