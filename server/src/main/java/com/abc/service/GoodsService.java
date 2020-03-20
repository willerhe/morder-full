package com.abc.service;

import com.abc.entity.Goods;
import com.abc.entity.SysPerm;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService {


    Goods getById(String id);

    List<Goods> getGoods(Page page, Goods goods);

    void saveOrUpdate(List<Goods> gs);

    void delete(List<Goods> goodsList);

    String uploadCover(String path,MultipartFile multipartFile);
}
