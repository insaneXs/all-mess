package com.insanexs.mess.concurrency.threadlocal;

/**
 * @ClassName ThreadLocalTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/4/7 9:00 上午
 */
public class ThreadLocalTest {

    public static void main(String[] args){
        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();

        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

        Thread thread1 = new Thread(()->{
            threadLocal1.set("Thread 1 Variable A");
            threadLocal2.set("Thread 1 Variable B");

            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
        });

        Thread thread2 = new Thread(()->{
           threadLocal1.set("Thread 2 Variable C");
           threadLocal2.set("Thread 2 Variable D");

           System.out.println(threadLocal1.get());
           System.out.println(threadLocal2.get());
        });

        thread1.start();

        thread2.start();

    }
}
