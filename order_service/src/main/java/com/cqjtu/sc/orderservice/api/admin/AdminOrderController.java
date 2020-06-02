package com.cqjtu.sc.orderservice.api.admin;

import com.cqjtu.sc.orderservice.service.AdminOrderService;
import com.cqjtu.sc.orderservice.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin/order")
@Validated
public class AdminOrderController {

    @Autowired
    AdminOrderService adminOrderService;

    /**
     * 查询订单
     *
     * @param userId
     * @param orderSn
     * @param orderStatusArray
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */

    @GetMapping("/list")
    public Object list(Integer supplierId,Integer userId, String orderSn,
                       @RequestParam(required = false) List<Integer> orderStatusArray,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order) {

        Object list = adminOrderService.list(supplierId,userId, orderSn, orderStatusArray, page, limit, sort, order);
        return list;
    }

    /**
     * 查询物流公司
     *
     * @return
     */
    @GetMapping("/channel")
    public Object channel() {
        return ResponseUtil.ok();
    }

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        System.out.println("id = "+id);
        Object detail = adminOrderService.detail(id);
        return detail;
    }

    /**
     * 订单退款
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @PostMapping("/refund")
    public Object refund(@RequestBody String body) {
        return ResponseUtil.ok();
    }

    /**
     * 发货
     *
     * @param body 订单信息，{ orderId：xxx, shipSn: xxx, shipChannel: xxx }
     * @return 订单操作结果
     */
    @PostMapping("/ship")
    public Object ship(@RequestBody String body) {
        Object ship = adminOrderService.ship(body);
        return ship;
    }


    /**
     * 回复订单商品
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("/reply")
    public Object reply(@RequestBody String body) {
        return ResponseUtil.ok();
    }
}
