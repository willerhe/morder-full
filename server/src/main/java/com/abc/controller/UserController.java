package com.abc.controller;

// 小程序用户

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.abc.annotation.PermInfo;
import com.abc.config.WxMaConfiguration;
import com.abc.dao.UserMapper;
import com.abc.entity.User;
import com.abc.entity.UserExample;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


//@PermInfo(value = "小程序用户")
//@RequiresPermissions("a:user")
@RestController
@RequestMapping("/wx")
public class UserController {

    @Autowired
    UserMapper userMapper;

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public Object login(String appid, String code, User userInfo) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);

            UserExample e = new UserExample();
            UserExample.Criteria c = e.createCriteria();
            c.andOpenidEqualTo(session.getOpenid());
            List<User> ul = userMapper.selectByExample(e);
            if (ul.size() == 0) {
                // 插入
                userInfo.setOpenid(session.getOpenid());
                userInfo.setIsDeleted(0);
                userInfo.setId(UUID.randomUUID().toString());
                userMapper.insert(userInfo);
            } else {
                c.andOpenidEqualTo(session.getOpenid());
                userInfo = userMapper.selectByExample(e).get(0);
            }

//            this.logger.info(session.getSessionKey());
//            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return userInfo;
        } catch (WxErrorException e) {
            return e.toString();
        }
    }
}
