package com.baomw.annotation;

import com.baomw.selector.CustomizedAopImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 描述:
 * aop开关注解
 *
 * @author baomw
 * @create 2018-11-18 下午 11:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomizedAopImportSelector.class)
public @interface EnableAop {
}
