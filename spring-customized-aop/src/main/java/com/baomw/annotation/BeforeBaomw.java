package com.baomw.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 描述:
 * 自定义前置通知注解类
 *
 * @author baomw
 * @create 2018-11-18 下午 11:12
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BeforeBaomw {
    String value() default "";
}
