package com.cqjtu.sc.gateway.web.service;

import com.cqjtu.sc.gateway.annotation.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="order-service")
public interface OrderService {
    @PostMapping(value = "wx/order/checkout",consumes = "application/json")
    Object checkout(@RequestParam("userId") Integer userId, @RequestBody String body);

    @PostMapping(value = "wx/order/submit",consumes = "application/json")
    Object submit(@RequestParam("userId") Integer userId, @RequestBody String body);
}
