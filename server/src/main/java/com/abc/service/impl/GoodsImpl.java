package com.abc.service.impl;

import com.abc.dao.GoodsMapper;
import com.abc.entity.Goods;
import com.abc.entity.GoodsExample;
import com.abc.entity.SysPerm;
import com.abc.service.GoodsService;
import com.abc.service.ICloud;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GoodsImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ICloud iCloud;

    @Override
    public Goods getById(String id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Goods> getGoods(Page page, Goods goods) {
        GoodsExample e = new GoodsExample();
        GoodsExample.Criteria c = e.createCriteria();
        c.andIsDeletedEqualTo(0);
        if (goods.getType() != null){
            c.andTypeEqualTo(goods.getType());
        }

        return goodsMapper.selectByExample(e);
    }

    @Override
    public void saveOrUpdate(List<Goods> gs) {
        for (int i = 0; i < gs.size(); i++) {
            if (gs.get(i).getId() == null) {
                // 插入
                gs.get(i).setId(UUID.randomUUID().toString());
                gs.get(i).setIsDeleted(0);
                gs.get(i).setCreated(new Date());
                goodsMapper.insert(gs.get(i));
            } else {
                // 更新
                goodsMapper.updateByPrimaryKey(gs.get(i));
            }
        }

    }

    @Override
    public void delete(List<Goods> goodsList) {
        for (int i = 0; i < goodsList.size(); i++) {
            Goods g = goodsMapper.selectByPrimaryKey(goodsList.get(i).getId());
            goodsList.get(i).setIsDeleted(1);
            goodsMapper.updateByPrimaryKey(goodsList.get(i));
        }
    }

    @Override
    public String uploadCover(String path, MultipartFile file) {

        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return "failed";
        }
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        try {
            file.transferTo(filePath);
            String outUrl = iCloud.uploadQiNiu(path + "/" + fileName);
            System.out.println("外链是" + outUrl);
            return outUrl;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }
}
