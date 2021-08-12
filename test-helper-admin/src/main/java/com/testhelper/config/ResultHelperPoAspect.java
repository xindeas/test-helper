package com.testhelper.config;

import com.testhelper.po.ResultHelperPo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Component
@Aspect
public class ResultHelperPoAspect {
    /**@Pointcut("execution(* com.testhelper.admin.controller.UserController.*(..))")*/
    @Pointcut("within(com.testhelper.admin.component..*)")
    private void exc() {}
    @Around("exc()")
    public Object aroundExc(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        Object[]args = proceedingJoinPoint.getArgs();
        try {
            result = proceedingJoinPoint.proceed(args);
        } catch(Throwable e) {
            e.printStackTrace();
            ResultHelperPo po = new ResultHelperPo(false, null, e.getMessage());
            result = po;
        }
        return result;
    }
}
