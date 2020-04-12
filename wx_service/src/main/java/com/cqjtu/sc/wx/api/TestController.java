package com.cqjtu.sc.wx.api;

import com.cqjtu.sc.wx.properties.WxProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    WxProperties wxProperties;
    @RequestMapping("test")
    public Object test(){
        return wxProperties;
    }
}
