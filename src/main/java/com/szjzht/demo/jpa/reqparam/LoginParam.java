package com.szjzht.demo.jpa.reqparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * @Auther: mayn
 * @Date: 2020/5/28 10:28
 * @Description:
 */
@ApiModel(value = "登陆请求参数")
public class LoginParam {

    @Pattern(regexp="^[a-zA-Z0-9_-]{1,32}$",message = "用户名格式不正确")
    @ApiModelProperty(name = "username",value = "用户名",required = true,dataType = "string")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Pattern(regexp="^[a-zA-Z0-9_-]{6,10}$",message = "密码格式不正确")
    @ApiModelProperty(name = "password",value = "密码",required = true,dataType = "string")
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
