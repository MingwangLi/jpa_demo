package com.szjzht.demo.jpa.aop;

import com.alibaba.fastjson.JSON;
import com.szjzht.demo.jpa.util.HttpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Component
@Aspect
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //第一个*号：表示返回类型 第二个*号：表示类名   *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
    @Pointcut("execution(* com.szjzht.demo.jpa.web.controller..*.*(..)) && @annotation(com.szjzht.demo.jpa.annotation.Log))")
    public void webLog() {
    }

    /**
     * 记录请求url、参数
     * @param joinPoint
     */
    @Before(value = "webLog()")
    public void logRequestInformation(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("---------------------------URL : " + request.getRequestURL().toString()+"---------------------------");
        logger.info("---------------------------HTTP_METHOD : " + request.getMethod()+"---------------------------");
        logger.info("---------------------------IP : " + HttpUtil.getIpAddress(request)+"---------------------------");
        logger.info("---------------------------CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+"---------------------------");
        String param = "";
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (null != parameterMap && parameterMap.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String paramName:parameterMap.keySet()) {
                sb.append(paramName).append(":").append(request.getParameter(paramName)).append(",");
            }
            param = sb.substring(0,sb.length()-1);
        }
        logger.info("---------------------------ARGS : " + param+"---------------------------");
    }

    @AfterReturning(returning = "object",pointcut = "webLog()")
    public void logRequestResult(JoinPoint joinPoint, Object object) {
        logger.info("---------------------------Method {} AfterReturning：响应参数为：{}",
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                JSON.toJSONString(object));
    }


}


