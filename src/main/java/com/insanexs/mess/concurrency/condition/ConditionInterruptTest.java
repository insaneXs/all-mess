package com.insanexs.mess.concurrency.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionInterruptTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/1/19 11:32 AM
 */
public class ConditionInterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        Thread waiterThread = new Thread(()->{
            lock.lock();

            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Waiter Thread release lock");
            lock.unlock();
        });


        Thread sleepThread = new Thread(()->{
            lock.lock();
            waiterThread.start();

            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Sleep Thread release lock");
            lock.unlock();
        });

        Thread lastAcquireThread = new Thread(()->{
            try {
                String result = lock.tryLock(10000L, TimeUnit.MILLISECONDS) ? "SUCCESS" : "FAILED";
                System.out.println("LastAcquireThread lock " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread interruptedThread = new Thread(()->{
            lock.lock();
            System.out.println("Interrupt Thread acquires lock");
            try {
                sleepThread.start();

                condition.await();
            } catch (InterruptedException e) {
                System.out.println("Interrupted Thread Throws Interrupt Exception");

                System.out.println("EVEN THOUGH CURRENT THREAD THROW EXCEPTION; LOCK STILL HELD BY CURRENT THREAD: " + ((ReentrantLock)lock).isHeldByCurrentThread());
                //only catch exception, but not release lock
                lastAcquireThread.start();
                System.out.println("Last Acquire Thread Start");
            }


        });


        interruptedThread.start();

        Thread.sleep(1000L);

        //虽然Main Threaed 一开始就将interrupted Thread 标记为interrupted ，
        //但是interrupted thread仍然会在AQS的队列中排队，直到轮到自己时才会throw InterruptException
        interruptedThread.interrupt();
        System.out.println("Main Thread Interrupt Interrupt Thread");


    }
}
