package com.abc.controller;

import com.abc.annotation.PermInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;

@PermInfo(value = "评论")
@RequiresPermissions("a:comment")
@RestController
public class CommentController {
}
