package com.insanexs.mess.threads.lock.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName LockSupportTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/1/13 5:15 PM
 */
public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(()->{

            LockSupport.park();

            while(true){
                System.out.println("Thread Executed");
            }
        });

        thread.setDaemon(false);
        thread.start();

        Thread.sleep(1000L);

        System.out.println("Before Main Thread Unpark, thread state is:" + thread.getState());

//        thread.interrupt();
        LockSupport.unpark(thread);

        Thread.sleep(100);
        System.out.println("After Main Thread Unpark, thread state is:" + thread.getState());
    }
}
