package com.ruslan.spring.mvc_hibernate_aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLoggingAspect {

    //для любых методов из любых классов пакета dao с любыми параметрами
    @Around("execution(* com.ruslan.spring.mvc_hibernate_aop.dao.*.* (..))")
    public Object aroundAllRepositoryMethodsAdvice(ProceedingJoinPoint pjp)
                throws Throwable {

        //получаем сигнатуру метода и из неё - имя метода:
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getName();

        System.out.println("Begin of " + methodName);
        //запуск таргет-метода и запись результата в переменную
        Object targetMethodResult = pjp.proceed();
        System.out.println("End of " + methodName);
        return targetMethodResult;
    }
}
