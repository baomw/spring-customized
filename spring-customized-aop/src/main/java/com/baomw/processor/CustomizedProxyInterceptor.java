package com.baomw.processor;

import com.baomw.holder.ProxyBeanHolder;
import com.baomw.util.ConfigurationUtil;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 描述:
 *  代理对象
 * @author baomw
 * @create 2018-11-19 下午 9:03
 */
public class CustomizedProxyInterceptor implements MethodInterceptor {
    //用于接收切面信息
    List<ProxyBeanHolder> proxyBeanHolderList;
   public CustomizedProxyInterceptor(List<ProxyBeanHolder> proxyBeanHolderList){
       this.proxyBeanHolderList = proxyBeanHolderList;
   }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //处理前置及环绕前置通知
        for (ProxyBeanHolder proxyBeanHolder: proxyBeanHolderList) {
            String annotationName = proxyBeanHolder.getAnnotationName();
            if (annotationName.equals(ConfigurationUtil.BEFORE)||annotationName.equals(ConfigurationUtil.AROUND))
                this.doProxy(proxyBeanHolder);
        }
        Object result = null;
        try{
            result = methodProxy.invokeSuper(o, objects);
        }catch (Exception e){
            System.out.println("get ex:"+e.getMessage());
            throw e;
        }
        //处理前置及环绕前置通知
        for (ProxyBeanHolder proxyBeanHolder: proxyBeanHolderList) {
            String annotationName = proxyBeanHolder.getAnnotationName();
            if (annotationName.equals(ConfigurationUtil.AFTER)||annotationName.equals(ConfigurationUtil.AROUND))
                this.doProxy(proxyBeanHolder);
        }
        return result;
}

    /**
     * 处理代理操作
     * @param proxyBeanHolder
     */
    private void doProxy(ProxyBeanHolder proxyBeanHolder){
        String className = proxyBeanHolder.getClassName();
        String methodName = proxyBeanHolder.getMethodName();
        Object classzz = null;
        try {
            classzz = Class.forName(className);
            Method[] methods = ((Class) classzz).getMethods();
            for (Method poxyMethod:methods)
                if (poxyMethod.getName().equals(methodName))
                    poxyMethod.invoke(((Class) classzz).newInstance(),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}