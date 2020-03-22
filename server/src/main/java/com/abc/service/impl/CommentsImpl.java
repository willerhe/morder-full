package com.abc.service.impl;

import com.abc.dao.CommentsMapper;
import com.abc.dao.OrdersMapper;
import com.abc.entity.Comments;
import com.abc.entity.CommentsExample;
import com.abc.entity.Goods;
import com.abc.entity.Orders;
import com.abc.service.CommentService;
import com.abc.service.ICloud;
import com.abc.service.OrderService;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentsImpl implements CommentService {

    @Autowired
    CommentsMapper commentsMapper;

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    ICloud iCloud;

    @Override
    public Comments getById(String id) {
        return commentsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Comments> getComments(Page page, Comments order) {
        CommentsExample e = new CommentsExample();
        CommentsExample.Criteria c = e.createCriteria();
        c.andIsDeletedEqualTo(0);
        if (order.getOid() != null && order.getOid().length() > 0) {
            c.andOidEqualTo(order.getOid());
        }
        if (order.getUid() != null && order.getUid().length() > 0) {
            c.andOidEqualTo(order.getUid());
        }
        // todo 根据goods id 模糊查询
        List<Comments> commentsList = commentsMapper.selectByExampleWithBLOBs(e);
        return commentsList;
    }

    @Override
    public void saveOrUpdate(List<Comments> gs) {
        for (int i = 0; i < gs.size(); i++) {
            if (gs.get(i).getId() == null) {
                gs.get(i).setId(UUID.randomUUID().toString());
                gs.get(i).setCreated(new Date());
                Orders orders = ordersMapper.selectByPrimaryKey(gs.get(i).getOid());
                JSONArray list = (JSONArray) JSON.parse(orders.getMeta());

                StringBuilder goodsIds = new StringBuilder();
                for (Object g : list) {
                    goodsIds.append((String) ((JSONObject) g).get("id")).append(",");
                }
                gs.get(i).setGoodsIds(goodsIds.toString());
                gs.get(i).setIsDeleted(0);
                commentsMapper.insert(gs.get(i));

                // 更新订单状态
                Orders o = ordersMapper.selectByPrimaryKey(gs.get(i).getOid());
                o.setStatus("已评价");
                ordersMapper.updateByPrimaryKey(o);
            } else {
                // 更新
                commentsMapper.updateByPrimaryKeyWithBLOBs(gs.get(i));
            }
        }

    }

    @Override
    public void delete(List<Comments> orderList) {
        for (int i = 0; i < orderList.size(); i++) {
            Comments g = commentsMapper.selectByPrimaryKey(orderList.get(i).getId());
            orderList.get(i).setIsDeleted(1);
            commentsMapper.updateByPrimaryKey(orderList.get(i));
        }
    }


}
