package com.szjzht.demo.jpa.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: mayn
 * @Date: 2020/5/27 15:56
 * @Description:
 */
@ApiModel
public class Result<T> {

    @ApiModelProperty(name = "code",value = "业务状态码",dataType = "string")
    private String code;
    @ApiModelProperty(name = "message",value = "返回信息",dataType = "string")
    private String message;
    @ApiModelProperty(name = "success",value = "是否成功",dataType = "boolean")
    private boolean success;
    @ApiModelProperty(name = "data",value = "返回数据")
    private T data;

    public Result () {

    }

    public static Result success() {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMsg());
        result.setSuccess(true);
        result.setData(null);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMsg());
        result.setSuccess(true);
        result.setData(data);
        return result;
    }


    public static Result fail(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMsg());
        result.setSuccess(false);
        result.setData(null);
        return result;
    }

    public static Result fail(String errorMsg) {
        Result result = new Result();
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMessage(errorMsg);
        result.setSuccess(false);
        result.setData(null);
        return result;
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
