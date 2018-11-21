package com.baomw.processor;

import com.baomw.util.ConfigurationUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.*;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * 描述:
 * aop实现核心处理类
 *
 * @author baomw
 * @create 2018-11-18 下午 11:24
 */
public class RealizedAopBeanPostProcessor implements BeanPostProcessor {


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        String targetClass = bean.getClass().getName();
        Object object = bean;
        if (ConfigurationUtil.classzzProxyBeanHolder.containsKey(targetClass)){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(object.getClass());
            enhancer.setCallback(new CustomizedProxyInterceptor(ConfigurationUtil.classzzProxyBeanHolder.get(targetClass)));
            object =  enhancer.create();
        }
        return object;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
