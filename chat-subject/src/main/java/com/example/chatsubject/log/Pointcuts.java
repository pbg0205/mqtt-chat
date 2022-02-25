package com.example.chatsubject.log;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Pointcut;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Pointcuts {

    @Pointcut("execution(* com.example.chatsubject..*Controller.*(..))")
    public void requestLogPointcut() {}

}
