package com.insanexs.mess.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierAwaitReturnIndexTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/2/4 10:36 AM
 */
public class CyclicBarrierAwaitReturnIndexTest {

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(4);
        Thread thread1 = new Thread(()->{
            try {
                System.out.println("Thread 1 await return:" + barrier.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(()->{
            try {
                System.out.println("Thread 2 await return:" + barrier.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(()->{
            try {
                System.out.println("Thread 3 await return:" + barrier.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        Thread thread4 = new Thread(()->{
            try {
                System.out.println("Thread 4 await return:" + barrier.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        thread1.start();

        Thread.sleep(10000L);

        thread2.start();

        Thread.sleep(10000L);

        thread3.start();

        Thread.sleep(10000L);

        thread4.start();

    }
}
