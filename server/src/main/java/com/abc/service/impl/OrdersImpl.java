package com.abc.service.impl;

import com.abc.dao.OrdersMapper;
import com.abc.entity.Orders;
import com.abc.entity.OrdersExample;
import com.abc.service.OrderService;
import com.abc.service.ICloud;
import com.abc.vo.Json;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersImpl implements OrderService {

    @Autowired
    OrdersMapper orderMapper;

    @Autowired
    ICloud iCloud;

    @Override
    public Orders getById(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Orders> getOrder(Page page, Orders order) {
        OrdersExample e = new OrdersExample();
        OrdersExample.Criteria c = e.createCriteria();
        c.andIsDeletedEqualTo(0);
        c.andUidEqualTo(order.getUid());
        if (order.getStatus() != null && !order.getStatus().equals("")) {
            c.andStatusEqualTo(order.getStatus());
        }
        List<Orders> ordersList = orderMapper.selectByExampleWithBLOBs(e);
        return ordersList;
    }

    @Override
    public void saveOrUpdate(List<Orders> gs) {
        for (int i = 0; i < gs.size(); i++) {
            if (gs.get(i).getId() == null) {
                // 插入
                gs.get(i).setId(UUID.randomUUID().toString());
                gs.get(i).setIsDeleted(0);
                gs.get(i).setCreated(new Date());
                long randomNum = System.currentTimeMillis();
                gs.get(i).setTitle(randomNum + "");
                gs.get(i).setCommentCount(0);
                gs.get(i).setStatus("待取货");
                gs.get(i).setCreated(new Date());
                OrdersExample e = new OrdersExample();
                OrdersExample.Criteria c = e.createCriteria();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date zero = calendar.getTime();

                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(new Date());
                calendar1.set(Calendar.HOUR_OF_DAY, 23);
                calendar1.set(Calendar.MINUTE, 59);
                calendar1.set(Calendar.SECOND, 59);
                Date end = calendar.getTime();

                c.andCreatedBetween(zero, end);
                int count = (int) orderMapper.countByExample(e) + 1000;
                gs.get(i).setCode(count);

                orderMapper.insert(gs.get(i));
            } else {
                // 更新
                orderMapper.updateByPrimaryKeyWithBLOBs(gs.get(i));
            }
        }

    }

    @Override
    public void delete(List<Orders> orderList) {
        for (int i = 0; i < orderList.size(); i++) {
            Orders g = orderMapper.selectByPrimaryKey(orderList.get(i).getId());
            orderList.get(i).setIsDeleted(1);
            orderMapper.updateByPrimaryKey(orderList.get(i));
        }
    }

    // 需要的参数
    // 1. 座位类型
    // 排队算法
    // 1. 默认15分钟每人
    // 2. 分餐桌计算排队
    // 3. 返回前面等待的人数
    // 4. 排队号码
    @Override
    public Json prepare(Orders order) {
        OrdersExample e = new OrdersExample();
        OrdersExample.Criteria c = e.createCriteria();
        c.andIsDeletedEqualTo(0);// 未删除
        c.andStatusEqualTo("待取货");
        c.andQueueTypeEqualTo(order.getQueueType());
        long count = orderMapper.countByExample(e);
        Json json = new Json();
        json.data("count", count);
        return json;
    }

}
