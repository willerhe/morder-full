package com.abc.service;

import com.abc.entity.Comments;
import com.abc.vo.Json;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface CommentService {


    Comments getById(String id);

    List<Comments> getComments(Page page, Comments comments);

    void saveOrUpdate(List<Comments> gs);

    void delete(List<Comments> commentsList);

}
