package com.cqjtu.sc.gateway.web.wx;


import com.cqjtu.sc.gateway.annotation.LoginUser;
import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务
 */
@RestController
@RequestMapping("/wx/user")
@Validated
public class WxUserController {


    @Autowired
    OrderService orderService;
    /**
     * 用户个人页面数据
     * <p>
     * 目前是用户订单统计信息
     *
     * @param userId 用户ID
     * @return 用户个人页面数据
     */
    @GetMapping("index")
    public Object list(@LoginUser Integer userId) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }

        return orderService.index(13);
    }

}