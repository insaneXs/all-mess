package com.insanexs.mess.javap;

/**
 * @Author: xieshang
 * @Description: 不同的同步方式在class文件中的区别
 * @Date: Create at 2020-07-14
 */
public class SynchronizedTest {

    public synchronized void synchronizedMethod(){
        return;
    }

    public static synchronized void staticSynchronizedMethod(){
        return;
    }

    public void synchronizedCode(){
        synchronized (this){
            return;
        }
    }

    public void staticSynchronizedCode(){
        synchronized (SynchronizedTest.class){
            return;
        }
    }
}
