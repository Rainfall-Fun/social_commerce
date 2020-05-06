package com.cqjtu.sc.gateway.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="wx-service")
public interface WXService {
    @PostMapping("/getOpenid")
    String getOpenid(@RequestParam("code") String code);
}
