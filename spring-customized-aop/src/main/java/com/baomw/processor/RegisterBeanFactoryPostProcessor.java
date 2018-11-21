package com.baomw.processor;

import com.baomw.holder.ProxyBeanHolder;
import com.baomw.util.ConfigurationUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * 描述:
 *
 * @author baomw
 * @create 2018-11-19 下午 1:59
 */
public class RegisterBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    /**
     * 存放需要代理的相关信息类
     */
    public static volatile List<ProxyBeanHolder> roxyBeanHolderList = new Vector<>();

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //获取所有的bdName
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName:beanDefinitionNames){
            BeanDefinition beanDefinition
                    = configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);
            //判断bd是否是一个注解bd
            if (beanDefinition instanceof AnnotatedBeanDefinition) {
                //取得bd上的所有注解
                AnnotationMetadata metadata = ((AnnotatedBeanDefinition) beanDefinition).getMetadata();
                Set<String> Annotations = metadata.getAnnotationTypes();
                //循环所有注解，找到aop切面注解类
                for (String annotation:Annotations)
                    if (annotation.equals(ConfigurationUtil.AOP_POINTCUT_ANNOTATION))
                        doScan((GenericBeanDefinition)beanDefinition);
            }
        }
    }

    /**
     * 扫描所有注解方法
     * @param beanDefinition
     */
    private void doScan(GenericBeanDefinition beanDefinition){
        try {
            String className = beanDefinition.getBeanClassName();
            Class<?> beanDefinitionClazz = Class.forName(className);
            Method[] methods = beanDefinitionClazz.getMethods();
            for (Method method :methods){
                Annotation[] annotations = method.getAnnotations();
                  for(Annotation annotation:annotations) {
                    String annotationName = annotation.annotationType().getName();
                    if(annotationName.equals(ConfigurationUtil.BEFORE)||annotationName.equals(ConfigurationUtil.AFTER)||
                            annotationName.equals(ConfigurationUtil.AROUND))
                        doScan(className,method,annotation);
                  }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 扫描出所有被代理的类
     * @param className
     * @param method
     * @param annotation
     */
    private void doScan(String className,Method method,Annotation annotation){
        ProxyBeanHolder proxyBeanHolder = new ProxyBeanHolder();
        proxyBeanHolder.setClassName(className);
        proxyBeanHolder.setMethodName(method.getName());
        proxyBeanHolder.setAnnotationName(annotation.annotationType().getName());
        //获取注解上的所有方法
        Method[] annotationMethods = annotation.annotationType().getDeclaredMethods();
        String packagePath = null;
        for (Method annotationMethod:annotationMethods) {
            if (annotationMethod.getName().equals("value")){
                try {
                    packagePath = (String) annotationMethod.invoke(annotation, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!packagePath.isEmpty()){
            String rootPath = this.getClass().getResource("/").getPath();
            String targetPackagePath = rootPath + packagePath.replace(".","/");
            File file = new File(targetPackagePath);
            File[] fileList = file.listFiles();
            List<ProxyBeanHolder> lroxyBeanHolderList = null;
            for (File wjFile:fileList) {
                if (wjFile.isFile()) {//判断是否为文件
                    String targetClass = packagePath+"."+wjFile.getName().replace(".class","");
                    try {
                        lroxyBeanHolderList = ConfigurationUtil.classzzProxyBeanHolder.get(targetClass);
                    }catch(Exception e){
                    }
                    if (lroxyBeanHolderList==null)
                        lroxyBeanHolderList = new Vector<>();
                    lroxyBeanHolderList.add(proxyBeanHolder);
                    ConfigurationUtil.classzzProxyBeanHolder.put(targetClass,lroxyBeanHolderList);
                }
            }

        }
    }
}
