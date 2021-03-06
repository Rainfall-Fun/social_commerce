package com.cqjtu.sc.gateway.web.service;

import com.cqjtu.sc.gateway.annotation.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="admin-goods-service")
public interface GoodsService {
    /**
     * 类似get
     * @param categoryId
     * @param brandId
     * @param keyword
     * @param isNew
     * @param isHot
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping(value = "/list")
    Object list(@RequestParam(value = "categoryId") Integer categoryId,
                @RequestParam(value = "brandId") Integer brandId,
                @RequestParam(value = "keyword") String keyword,
                @RequestParam(value = "isNew") Boolean isNew,
                @RequestParam(value = "isHot") Boolean isHot,
                @RequestParam(value = "page",defaultValue = "1") Integer page,
                @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                @RequestParam(value = "sort",defaultValue = "add_time") String sort,
                @RequestParam(value = "order",defaultValue = "desc") String order);

    /**
     * 类似post
     * @param data
     * @return
     */
    @RequestMapping(value = "/admin/goods/create",method = RequestMethod.POST,consumes = "application/json")
    Object create(@RequestBody String data);

    @GetMapping("/wx/goods/detail")
    Object detail(@RequestParam("userId") Integer userId,
                  @RequestParam("id") Integer id);

    @GetMapping("/wx/home/index")
    Object index(@RequestParam("userInfoId") Integer userInfoId);



    @GetMapping(value = "wx/cart/add",consumes = "application/json")
    Object add(@RequestParam("userInfoId") Integer userInfoId, @RequestBody String cart);

    @GetMapping(value = "wx/cart/goodscount")
    Object goodscount(@RequestParam("userInfoId") Integer userInfoId);

    @GetMapping(value = "wx/cart/index")
    Object cartIndex(@RequestParam("userInfoId") Integer userInfoId);

}
