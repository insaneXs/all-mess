package com.insanexs.mess.threads.state;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: insaneXs
 * @Description:
 * @Date: Create at 2020-01-07
 */
public class ThreadStateTest {

    public static void main(String[] args){
        threadStateNew();

        workingThread();

        threadStateTerminate();

        threadBlockedByLock();

        threadBlockedBySynchronized();

        threadSleep();

        threadWait();

        threadTimedWait();
    }

    private static void threadStateNew(){
        System.out.println("--------------------------");
        System.out.print("Never Start Thread State：");
        Thread thread = new Thread(()->{

        }, "Thread Never Start");
        //print NEW
        System.out.println(thread.getState());
        System.out.println("--------------------------");
    }

    private static void workingThread(){
        System.out.println("--------------------------");
        Thread thread = new Thread(()->{
           for(int i=0; i<100; i++){
               doSomeElse();
           }
        });

        thread.start();

        doSomeElse();
        //print RUNNABLE
        System.out.println("Working Thread State:" + thread.getState());
        System.out.println("--------------------------");
    }

    private static void threadStateTerminate(){
        System.out.println("--------------------------");
        System.out.print("Finish Job Thread State：");
        Thread thread = new Thread(()->{

        }, "Thread Finish Job");
        thread.start();


        try {
            //Main Thread Will Wait util this thread finished job
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //print TERMINATED
        System.out.println(thread.getState());
        System.out.println("--------------------------");
    }

    private static void threadBlockedByLock(){
        System.out.println("--------------------------");
        System.out.print("Thread State Blocked By Lock：");
        ReentrantLock lock = new ReentrantLock();
        Thread thread = new Thread(()->{
                lock.lock();
        }, "Blocked Thread");

        lock.lock();

        thread.start();

        doSomeElse();
        //print WAITING
        System.out.println(thread.getState());

        lock.unlock();
        System.out.println("--------------------------");
    }

    private static void threadBlockedBySynchronized(){
        System.out.println("--------------------------");
        System.out.print("Thread Blocked By Synchronized：");
        Thread thread = new Thread(()->{
            synchronized (ThreadStateTest.class){

            }
        }, "Blocked by Synchronized Thread");

        synchronized (ThreadStateTest.class){
            thread.start();
            doSomeElse();
            //print BLOCKED
            System.out.println(thread.getState());
        }
        System.out.println("--------------------------");
    }

    private static void threadSleep(){
        System.out.println("--------------------------");
        System.out.print("Sleeping Thread:");
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread sleep");

        thread.start();
        doSomeElse();
        //print TIMED_WAITING
        System.out.println(thread.getState());
        System.out.println("--------------------------");
    }


    private static void threadWait(){
        System.out.println("--------------------------");
        System.out.print("Thread Waiting:");
        Object lock = new Object();
        Thread threadA = new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();

                    for(int i=0; i<100; i++){
                        doSomeElse();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "Thread Waiting");

        Thread threadB = new Thread(()->{
            synchronized (lock){
                //print WAITING
                System.out.println("Before Notify, Thread A State:" + threadA.getState());
                lock.notify();
                //print BLOCKED
                System.out.println("After Notify, Thread A State:" + threadA.getState());
            }
        });


        threadA.start();
        doSomeElse();
        threadB.start();

        try {
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //print RUNNABLE
        System.out.println("After Thread B finish job, Thread A State:" + threadA.getState());
        System.out.println("--------------------------");
    }

    private static void threadTimedWait(){
        System.out.println("--------------------------");
        System.out.print("Thread Waiting:");
        Object lock = new Object();
        Thread threadA = new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait(1000);

                    for(int i=0; i<100; i++){
                        doSomeElse();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "Thread Waiting");

        Thread threadB = new Thread(()->{
            synchronized (lock){
                //print TIMED_WAITING
                System.out.println("Before Notify, Thread A State:" + threadA.getState());
                lock.notify();
                //print BLOCKED
                System.out.println("After Notify, Thread A State:" + threadA.getState());
            }
        });


        threadA.start();
        doSomeElse();
        threadB.start();

        try {
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //print RUNNABLE
        System.out.println("After Thread B finish job, Thread A State:" + threadA.getState());
        System.out.println("--------------------------");
    }
    /**
     * take some times, let the thread get cpu time
     */
    private static void doSomeElse(){
        double meanless = 0d;
        for(int i=0; i<10000; i++){
            meanless += Math.random();
        }
    }

}
