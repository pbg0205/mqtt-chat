package com.example.chatsubject.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Order(2)
@Component
public class StopWatchLogAspect {

    @Around("Pointcuts.stopWatchLogPointcut()")
    public Object stopWatchRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        log.info("{} - elapsed time : {}ms",joinPoint.getTarget(), stopWatch.getTotalTimeMillis());

        return result;
    }
}
