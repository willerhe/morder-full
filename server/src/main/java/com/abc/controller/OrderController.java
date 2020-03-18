package com.abc.controller;

import com.abc.annotation.PermInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;

@PermInfo(value = "订单")
@RequiresPermissions("a:order")
@RestController
public class OrderController {
}
