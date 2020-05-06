package com.cqjtu.sc.gateway.web.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/region")
public class AdminRegionController {
    @GetMapping("list")
    public Object list(){
        System.out.println("jin");
        return null;
    }
}
