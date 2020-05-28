package com.szjzht.demo.jpa.annotation;

import java.lang.annotation.*;

/**
 * @Auther: mayn
 * @Date: 2020/5/27 15:43
 * @Description:
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
}
