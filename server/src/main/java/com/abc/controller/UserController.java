package com.abc.controller;

// 小程序用户

import com.abc.annotation.PermInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PermInfo(value = "小程序用户")
@RequiresPermissions("a:user")
@RestController
public class UserController {
}
