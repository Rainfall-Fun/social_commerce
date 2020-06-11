package com.cqjtu.sc.gateway.web.admin;

import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.web.service.ComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2020/6/8
 */
@RestController
@RequestMapping("/admin/priceComparison/")
@Validated
public class AdminComparisonController {

    @Autowired
    private ComparisonService service;

    @PostMapping("/comparison/merchant")
    public Object comparisonToMerchant(HttpServletRequest request,@RequestBody String body) {
        System.out.println(request.getHeader("content-type"));
        return ResponseUtil.ok(service.comparisonToMerchant(body));
    }

    @GetMapping("/comparison/history_price/{id}")
    public Object comparisonToHistoryData(@PathVariable("id") String commodityId) {
        return ResponseUtil.ok(service.comparisonToHistoryData(commodityId));
    }


}
