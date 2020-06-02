package com.cqjtu.sc.gateway.web.service;

import com.cqjtu.sc.gateway.annotation.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(name = "order-service")
public interface OrderService {
    @GetMapping("wx/order/list")
    Object list(@RequestParam Integer userId,
               @RequestParam(defaultValue = "0") Integer showType,
               @RequestParam(defaultValue = "1") Integer page,
               @RequestParam(defaultValue = "10") Integer limit,
               @RequestParam(defaultValue = "add_time") String sort,
               @RequestParam(defaultValue = "desc") String order);

    @PostMapping(value = "wx/order/checkout", consumes = "application/json")
    Object checkout(@RequestParam("userId") Integer userId, @RequestBody String body);

    @PostMapping(value = "wx/order/submit", consumes = "application/json")
    Object submit(@RequestParam("userId") Integer userId, @RequestBody String body);

    @GetMapping(value = "wx/order/detail")
    Object detail(@RequestParam("userId") Integer userId, @RequestParam("orderId") Integer orderId);

    @PostMapping(value = "wx/order/prepay", consumes = "application/json")
    Object prepay(@RequestParam("userId") Integer userId, @RequestBody String body);
    @PostMapping(value = "wx/order/confirm", consumes = "application/json")
    Object confirm(@RequestParam("userId") Integer userId, @RequestBody String body);
    @PostMapping(value = "wx/order/refund", consumes = "application/json")
    Object refund(@RequestParam("userId") Integer userId, @RequestBody String body);

    @GetMapping("wx/order/index")
    Object index(@RequestParam("userId") Integer userId);
    @GetMapping("wx/order/goods")
    Object goods(@RequestParam("userId") Integer userId,
                 @RequestParam("orderId") Integer orderId);

    @PostMapping(value = "wx/order/comment",consumes = "application/json")
    Object comment(@RequestParam("userId") Integer userId, @RequestBody String body);
    @GetMapping("admin/order/list")
    Object adminList(@RequestParam("supplierId") Integer supplierId,
                     @RequestParam("userId") Integer userId,
                     @RequestParam("orderSn") String orderSn,
                     @RequestParam(required = false) List<Short> orderStatusArray,
                     @RequestParam(defaultValue = "1") Integer page,
                     @RequestParam(defaultValue = "10") Integer limit,
                     @RequestParam(defaultValue = "add_time") String sort,
                     @RequestParam(defaultValue = "desc") String order);

    @GetMapping("admin/order/detail")
    Object adminDetail(@RequestParam("id") Integer id);

    @PostMapping("admin/order/refund")
    Object adminRefund(@RequestBody String body);

    @PostMapping("admin/order/ship")
    Object adminShip(@RequestBody String body);

    @PostMapping("admin/order/reply")
    Object adminReply(@RequestBody String body);
}
