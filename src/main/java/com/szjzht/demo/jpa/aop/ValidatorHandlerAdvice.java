package com.szjzht.demo.jpa.aop;

import com.szjzht.demo.jpa.common.Result;
import com.szjzht.demo.jpa.common.ResultEnum;
import com.szjzht.demo.jpa.exception.BusinessException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @Auther: mayn
 * @Date: 2020/5/28 10:23
 * @Description:
 */
@RestControllerAdvice
public class ValidatorHandlerAdvice {

    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException ex) {
        StringBuilder sb = new StringBuilder();
        for(ObjectError error:ex.getBindingResult().getAllErrors()){
            sb.append(error.getDefaultMessage()).append(",");
        }
        String message = sb.substring(0,sb.length()-1);
        return Result.fail(message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation constraintViolation:constraintViolations) {
            String message = constraintViolation.getMessage();
            sb.append(message).append(",");
        }
        String message = sb.substring(0,sb.length()-1);
        return Result.fail(message);
    }

    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException businessException) {
        Result result = businessException.getResult();
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Result handleBusinessException(Exception exception) {
        exception.printStackTrace();
        return Result.fail(ResultEnum.SERVER);
    }


}


