package com.example.jhyangnewthings.constructor.aop;

import com.example.jhyangnewthings.constructor.logqueue.LogBean;
import com.example.jhyangnewthings.constructor.logqueue.LogQueue;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author jhYang
 * @Description  关于接口调用时的切面
 * @Date 2020/3/28
 */

//@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Component
public class requestAopInController {
    private static Logger log = LoggerFactory.getLogger(requestAopInController.class);

    @Pointcut("execution(* com.example.jhyangnewthings.api..*(..))")
    public void PointCut() {
    }

    @Before(value = "PointCut()")
    public void doBefore(JoinPoint joinPoint) {
        joinPoint.getSignature().getName();

        LogBean logBean = new LogBean("张三", "01", "15.75.0.178", "20200421");
        try {
//            LogQueue.push(logBean);
            log.info("【requestAopInController】切面消息入队列,当前大小："+ LogQueue.size());
        } catch (Exception e) {
            log.error("【requestAopInController】消息入队列出错...");
        }
    }

    @After(value = "PointCut()")
    public void doAfter(JoinPoint joinPoint) {
//        log.info("doAfter");
    }
}
