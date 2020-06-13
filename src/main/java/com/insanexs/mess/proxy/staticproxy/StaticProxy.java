package com.insanexs.mess.proxy.staticproxy;

import com.insanexs.mess.proxy.SomeService;

/**
 * @ClassName StaticProxy
 * @Description TODO
 * @Author xieshang
 * @Date 2020/5/6 10:35 上午
 */
public class StaticProxy implements SomeService {

    private SomeService target;

    public StaticProxy(SomeService someService){
        this.target = someService;
    }

    @Override
    public void doSomeThing() {
        System.out.println("Static Proxy Do Something");
        target.doSomeThing();
    }
}
