package com.cqjtu.sc.orderservice.task;


import com.cqjtu.sc.orderservice.db.domain.AllOrder;
import com.cqjtu.sc.orderservice.db.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
public class StaticScheduleTask {

    @Autowired
    private OrderService orderService;
    @Autowired
    private TaskService taskService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void orderTasks(){
        List<AllOrder> orderList = orderService.queryAllUnpaid();
        for(AllOrder order : orderList){
            LocalDateTime add = order.getGenTime();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expire =  add.plusMinutes(1);
            if(expire.isBefore(now)) {
                // 已经过期，则加入延迟队列
                taskService.addTask(new OrderUnpaidTask(order.getOrderId(), 0));
            }
        }
    }
}