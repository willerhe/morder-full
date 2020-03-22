package com.abc.controller;

import com.abc.entity.Comments;
import com.abc.service.CommentService;
import com.abc.vo.Json;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CommentController {
    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    CommentService commentService;

    @GetMapping("/comments")
    public Json query(Page<Comments> page, Comments comment) {

        page.setRecords(commentService.getComments(page, comment));
        return Json.succ("query comment").data("page", page);
    }

    @PutMapping("/comments")
    public Json update(@RequestBody List<Comments> commentList) {
        commentService.saveOrUpdate(commentList);
        return Json.succ();
    }

    @PostMapping("/comments")
    public Json insert(@RequestBody List<Comments> commentList) {
        commentService.saveOrUpdate(commentList);
        return Json.succ();
    }

    @DeleteMapping("/comments")
    public Json delete(@RequestBody List<Comments> commentList) {
        commentService.delete(commentList);
        return Json.succ();
    }


}
