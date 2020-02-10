package com.insanexs.mess.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/1/22 4:43 PM
 */
public class CountDownLatchTest {

    public static void main(String[] args){
        testAwait();
    }

    protected static void testAwait(){
        CountDownLatch latch = new CountDownLatch(1);

        Thread newThread = new Thread(()->{
            try {
                System.out.println("new thread start await,timestamp=>" + System.currentTimeMillis());
                latch.await();
                System.out.println("new thread finished await, timestamp=>" + System.currentTimeMillis());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread = new Thread(()->{
            try {
                latch.await();
                System.out.println("Thread Back From Await");
                newThread.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();


    }
}
