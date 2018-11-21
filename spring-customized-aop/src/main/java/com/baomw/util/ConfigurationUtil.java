package com.baomw.util;

import com.baomw.holder.ProxyBeanHolder;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 *
 * @author baomw
 * @create 2018-11-19 下午 1:48
 */
public class ConfigurationUtil {

    /**
     * aop标识注解类
     */
    public static final String AOP_POINTCUT_ANNOTATION
                                            = "com.baomw.annotation.AopJ";
    /**
     * 前置通知注解类
     */
    public static final String BEFORE
                                            = "com.baomw.annotation.BeforeBaomw";
    /**
     * 后置通知注解类
     */
    public static final String AFTER
                                            = "com.baomw.annotation.AfterBaomw";
    /**
     * 环绕通知注解类
     */
    public static final String AROUND
                                            = "com.baomw.annotation.AroundBaomw";
    /**
     * 存放需代理的全部目标对象类
     */
    public static volatile Map<String,List<ProxyBeanHolder>> classzzProxyBeanHolder = new ConcurrentHashMap<>();

}
