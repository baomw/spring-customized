package com.baomw.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 描述:
 * 自定义环绕通知处理注解类
 *
 * @author baomw
 * @create 2018-11-18 下午 11:19
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AroundBaomw {
    String value() default "";
}
