package com.baomw.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * √Ë ˆ:
 *
 * @author baomw
 * @create 2018-11-19 …œŒÁ 10:15
 */
@Component
@Aspect
public class PointCutE {

    @Pointcut("execution(* com.baomw.dao.*.*(..))")
    public void beforePointCut(){

    }
    @Pointcut("execution(* com.baomw.dao.*.*(..))")
    public void afterPointCut(){

    }

    @Before("beforePointCut()")
    @After("afterPointCut()")
    public void before(){
        System.out.println("≤‚ ‘œ»÷¥––");
    }

}
