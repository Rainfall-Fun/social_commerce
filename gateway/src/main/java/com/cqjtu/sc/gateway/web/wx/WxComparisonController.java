package com.cqjtu.sc.gateway.web.wx;

import com.cqjtu.sc.gateway.util.ResponseUtil;
import com.cqjtu.sc.gateway.web.service.ComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/6/8
 */
@RestController
@RequestMapping("/wx/priceComparison/")
@Validated
public class WxComparisonController {

    @Autowired
    private ComparisonService service;

    @PostMapping("/comparison/customer")
    public Object comparisonToCustomer(@RequestBody String body){
        return ResponseUtil.ok(service.comparisonToCustomer(body));
    }
}
