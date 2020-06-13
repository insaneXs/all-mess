package com.insanexs.mess.proxy;

import redis.clients.jedis.ScanResult;

/**
 * @ClassName SomeServiceImpl
 * @Description TODO
 * @Author xieshang
 * @Date 2020/5/6 10:29 上午
 */
public class SomeServiceImpl implements SomeService{

    @Override
    public void doSomeThing() {
        System.out.println("Target Object Do SomeThing");
    }
}
