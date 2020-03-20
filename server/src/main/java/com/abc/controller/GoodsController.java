package com.abc.controller;

import com.abc.annotation.PermInfo;
import com.abc.entity.Goods;
import com.abc.entity.SysUser;
import com.abc.service.GoodsService;
import com.abc.service.impl.GoodsImpl;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


//@PermInfo(value = "商品")
//@RequiresPermissions("a:goods")
@RestController
public class GoodsController {
    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    GoodsService goodsService;

    @GetMapping("/goods")
    public Json query(Page<Goods> page, Goods goods) {

        page.setRecords(goodsService.getGoods(page, goods));
        return Json.succ("query goods").data("page", page);
    }

    @PutMapping("/goods")
    public Json update(List<Goods> goodsList) {
        goodsService.saveOrUpdate(goodsList);
        return Json.succ();
    }

    @PostMapping("/goods")
    public Json insert(@RequestBody List<Goods> goodsList) {
        goodsService.saveOrUpdate(goodsList);
        return Json.succ();
    }

    @DeleteMapping("/goods")
    public Json delete(@RequestBody  List<Goods> goodsList) {
        goodsService.delete(goodsList);
        return Json.succ();
    }

    @PostMapping("/goods/cover")
    public Json cover(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        String path = req.getServletContext().getRealPath(".");

        return Json.succ(goodsService.uploadCover(path,file));
    }

}
