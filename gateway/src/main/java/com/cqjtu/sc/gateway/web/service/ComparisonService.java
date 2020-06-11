package com.cqjtu.sc.gateway.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Administrator
 * @date 2020/6/8
 */
@FeignClient(name = "price-comparison-server")
public interface ComparisonService {

    @PostMapping(value = "comparison/merchant",consumes = MediaType.APPLICATION_JSON_VALUE)
    // @PostMapping(value = "comparison/merchant",headers = {"ContentType=application/json;charset=UTF-8"})
    Object comparisonToMerchant(@RequestBody String body);

    @GetMapping(value = "history_price/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    Object comparisonToHistoryData(@PathVariable("id") String id);

    @PostMapping(value = "comparison/customer",consumes = MediaType.APPLICATION_JSON_VALUE)
    Object comparisonToCustomer(@RequestBody String body);
}
