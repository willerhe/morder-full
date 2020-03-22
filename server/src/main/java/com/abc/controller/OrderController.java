package com.abc.controller;

import com.abc.entity.Orders;
import com.abc.service.OrderService;
import com.abc.vo.Json;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public Json query(Page<Orders> page, Orders order) {

        page.setRecords(orderService.getOrder(page, order));
        return Json.succ("query order").data("page", page);
    }
    @GetMapping("/order/prepare")
    public Json prepare(Orders order) {

        Json result = orderService.prepare(order);
        return Json.succ("query order").data(result);
    }

    @PutMapping("/order")
    public Json update(@RequestBody List<Orders> orderList) {
        orderService.saveOrUpdate(orderList);
        return Json.succ();
    }

    @PostMapping("/order")
    public Json insert(@RequestBody List<Orders> orderList) {
        orderService.saveOrUpdate(orderList);
        return Json.succ();
    }

    @DeleteMapping("/order")
    public Json delete(@RequestBody List<Orders> orderList) {
        orderService.delete(orderList);
        return Json.succ();
    }


}
