package com.szjzht.demo.jpa.common;

/**
 * @Auther: mayn
 * @Date: 2020/5/27 18:55
 * @Description:
 */
public enum ResultEnum {

    SUCCESS("00","OK"),
    SERVER("01","SERVER IS UNDER MAINTENANCE"),
    FAIL("02","参数错误"),

    USERNOTFOUND("1000","用户名、密码错误"),
    USEREXISTS("1001","用户名已存在"),
    ;


    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
