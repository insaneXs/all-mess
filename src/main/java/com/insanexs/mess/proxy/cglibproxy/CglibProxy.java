package com.insanexs.mess.proxy.cglibproxy;

import com.insanexs.mess.proxy.SomeService;
import com.insanexs.mess.proxy.SomeServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @ClassName CglibProxy
 * @Description TODO
 * @Author xieshang
 * @Date 2020/5/6 3:54 下午
 */
public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("Cglib Proxy Do Something");

        Object ret =  methodProxy.invokeSuper(o, objects);

        return ret;
    }

    public SomeService getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SomeService.class);
        enhancer.setCallback(this);

        return (SomeService) enhancer.create();
    }

    public static void main(String[] args) throws IOException {
        CglibProxy cglib = new CglibProxy();

        SomeService proxy = cglib.getProxy();
        //proxy.doSomeThing();

        System.in.read();
    }
}
