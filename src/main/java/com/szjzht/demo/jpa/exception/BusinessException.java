package com.szjzht.demo.jpa.exception;

import com.szjzht.demo.jpa.common.Result;
import com.szjzht.demo.jpa.common.ResultEnum;

/**
 * @Auther: mayn
 * @Date: 2020/5/28 15:02
 * @Description:
 */
public class BusinessException extends RuntimeException {

    private Result result;

    public BusinessException(ResultEnum resultEnum) {
        this.result = Result.fail(resultEnum);
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
