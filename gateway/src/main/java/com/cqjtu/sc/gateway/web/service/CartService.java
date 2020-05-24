package com.cqjtu.sc.gateway.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;

//@FeignClient(name="admin-goods-service")
public interface CartService {
    @GetMapping("wx/cart/add")
    Object add(@RequestParam("userInfoId") Integer userInfoId, @RequestBody String cart);
}
