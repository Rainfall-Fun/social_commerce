package com.cqjtu.sc.admin.goods.api;

import com.cqjtu.sc.admin.goods.dto.GoodsAllinone;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GoodsController {
    @RequestMapping("list")
    public Object test(@RequestParam(value = "categoryId") Integer categoryId,
                       @RequestParam(value = "brandId") Integer brandId,
                       @RequestParam(value = "keyword") String keyword,
                       @RequestParam(value = "isNew") Boolean isNew,
                       @RequestParam(value = "isHot") Boolean isHot,
                       @RequestParam(value = "page",defaultValue = "1") Integer page,
                       @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                       @RequestParam(value = "sort",defaultValue = "add_time") String sort,
                       @RequestParam(value = "order",defaultValue = "desc") String order){
        System.out.println(categoryId+"\n"+
                brandId+"\n"+
                keyword+"\n"+
                isNew+"\n"+
                isHot+"\n"+
                page+"\n"+
                limit+"\n"+
                sort+"\n"+
                order);
        List<Map<String,String>> data=new ArrayList<>();
        Map<String,String> m=new HashMap<>();
        m.put("id","1");
        m.put("goodsName","哇哈哈");
        for (int i = 0; i < 10; i++) {
            data.add(m);
        }
        return data;
    }
    @PostMapping("/create")
    public Object create(@RequestBody GoodsAllinone goodsAllinone) {
        System.out.println(goodsAllinone.toString());
        return true;
    }


}
