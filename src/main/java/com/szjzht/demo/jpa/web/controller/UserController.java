package com.szjzht.demo.jpa.web.controller;

import com.szjzht.demo.jpa.annotation.Log;
import com.szjzht.demo.jpa.common.Result;
import com.szjzht.demo.jpa.common.ResultEnum;
import com.szjzht.demo.jpa.entity.User;
import com.szjzht.demo.jpa.reqparam.LoginParam;
import com.szjzht.demo.jpa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



/**
 * @Auther: mayn
 * @Date: 2020/5/27 15:41
 * @Description:
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation("登陆")
    @Log
    @PostMapping("/login")
    public Result login(@Valid LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        User user = userService.getUserByUsername(username);
        if (null == user) {
            return Result.fail(ResultEnum.USERNOTFOUND);
        }
        String realPassword = user.getPassword();
        if (!password.equals(realPassword)) {
            return Result.fail(ResultEnum.USERNOTFOUND);
        }
        return Result.success(user.getNickname());
    }
}
