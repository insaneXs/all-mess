package com.insanexs.mess.proxy.jdkproxy;

import com.insanexs.mess.proxy.SomeService;
import com.insanexs.mess.proxy.SomeServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName JDKProxy
 * @Description TODO
 * @Author xieshang
 * @Date 2020/5/6 10:46 上午
 */
public class MyInvocationHandler implements InvocationHandler {

    private SomeService target;

    public MyInvocationHandler(SomeService someService){
        this.target = someService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK Proxy Do Something");

        return method.invoke(target, args);
    }

    public SomeService getProxy(){
        return (SomeService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) throws IOException {
        MyInvocationHandler ih = new MyInvocationHandler(new SomeServiceImpl());

        ih.getProxy().doSomeThing();

        System.in.read();
    }
}
