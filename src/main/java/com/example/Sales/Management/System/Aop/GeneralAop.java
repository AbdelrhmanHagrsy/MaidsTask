package com.example.Sales.Management.System.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GeneralAop {
    private final Logger logger = LoggerFactory.getLogger(Logger.class);

    @Pointcut("execution(* com.example.Sales.Management.System.service.BookService.*(..))")
    public void bookServiceMethods() {}

    @Pointcut("execution(* com.example.Sales.Management.System.service.PatronService.*(..))")
    public void patronServiceMethods() {}

    @Pointcut("execution(* com.example.Sales.Management.System.service.BorrowingRecordService.*(..))")
    public void borrowServiceMethods() {}

    @Before("bookServiceMethods() || patronServiceMethods() || borrowServiceMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        logger.info("Method {} is called with arguments {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "bookServiceMethods() || patronServiceMethods() || borrowServiceMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method {}: {}", joinPoint.getSignature().toShortString(), exception.getMessage());
    }

}
