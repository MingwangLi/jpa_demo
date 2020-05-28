package com.szjzht.demo.jpa.web.controller;

import com.szjzht.demo.jpa.annotation.Log;
import com.szjzht.demo.jpa.common.Result;
import com.szjzht.demo.jpa.common.ResultEnum;
import com.szjzht.demo.jpa.entity.User;
import com.szjzht.demo.jpa.reqparam.LoginParam;
import com.szjzht.demo.jpa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


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

    @ApiOperation("创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="用户名",dataType="string", paramType = "query", required = true),
            @ApiImplicitParam(name="password",value="密码",dataType="string", paramType = "query", required = true),
            @ApiImplicitParam(name="nickname",value="昵称",dataType="string", paramType = "query", required = true)
    })
    @Log
    @PostMapping
    public Result create(@NotBlank(message = "用户名不能为空")String username, @NotBlank(message = "密码不能为空")String password,
                         @NotBlank(message = "昵称不能为空")String nickname) {
        User user = userService.getUserByUsername(username);
        if (null != user) {
            return Result.fail(ResultEnum.USEREXISTS);
        }
        user = new User(username,password,nickname);
        userService.saveUser(user);
        return Result.success();
    }
}
