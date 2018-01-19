package org.koma.javamyadmin.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * JavaMyAdmin 日志模块
 * 该模块用来为整个应用的入口和出口处打相应的日志
 *
 * 采用 AspectJ 方式来实现 AOP
 * 这里 @Component 表示首先将该 Bean 交给 Spring 管理
 * 然后再通过 @Aspect 指示 Spring 创建对应的代理,二者都不可省去
 *
 * @author koma <komazhang@foxmail.com>
 */
@Slf4j
@Aspect
@Component
public class JavaMyAdminLog {
    @Pointcut("execution(* org.koma.javamyadmin.controller..*(..))")
    public void controllerLogger() {}

    /**
     * 前置动作
     */
    @Before("controllerLogger()")
    public void requestLog() {
        System.out.println("start exec controller...");
    }

    @Around("controllerLogger()")
    public void aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("show log...");
        System.out.println("start exec join point method...");
        Object returnVal = joinPoint.proceed(); //exec join point
        System.out.println("end exec join point method...");
    }
}
