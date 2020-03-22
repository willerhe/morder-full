package com.abc.service;

import com.abc.entity.Orders;
import com.abc.vo.Json;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface OrderService {


    Orders getById(String id);

    List<Orders> getOrder(Page page, Orders order);

    void saveOrUpdate(List<Orders> gs);

    void delete(List<Orders> orderList);

    Json prepare(Orders order);
}
