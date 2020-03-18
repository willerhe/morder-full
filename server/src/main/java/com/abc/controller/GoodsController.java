package com.abc.controller;

import com.abc.annotation.PermInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;


@PermInfo(value = "商品")
@RequiresPermissions("a:goods")
@RestController
public class GoodsController {
}
