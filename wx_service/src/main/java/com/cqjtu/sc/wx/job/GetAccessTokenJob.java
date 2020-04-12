package com.cqjtu.sc.wx.job;

import com.cqjtu.sc.wx.properties.WxProperties;
import com.cqjtu.sc.wx.token.WxToken;
import com.cqjtu.sc.wx.util.HttpUtil;
import com.cqjtu.sc.wx.util.JacksonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

/**
 * 获取微信的access_token
 */
@Component
public class GetAccessTokenJob {
    @Autowired
    WxProperties wxProperties;
    @Scheduled(fixedRate = 10000*7100)
    public void getAccessToken(){
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+wxProperties.appID+"&secret="+wxProperties.appsecret;
        try {
            String result = HttpUtil.doGet(url);
            if (!StringUtils.isBlank(result)){
                String accessToken = JacksonUtil.parseString(result, "access_token");
                WxToken.accessToken=accessToken;
                System.out.println("获取到了accessToken "+WxToken.accessToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
