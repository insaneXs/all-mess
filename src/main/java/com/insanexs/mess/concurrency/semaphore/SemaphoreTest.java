package com.insanexs.mess.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/2/4 11:46 AM
 */
public class SemaphoreTest {


    public static void main(String[] args){
        System.out.println("******************NORMAL USE******************");
        //normalUse();

        System.out.println("*******************REENTRANT******************");
        //reentrantTest();

        System.out.println("********************RELEASE WITHOUT ACQUIRE******************");
        releaseWithoutAcquire();
    }

    protected static void normalUse(){

        Semaphore semaphore = new Semaphore(2);

        Thread thread1 = new Thread(()->{
            System.out.println("Thread 1 Start");
            try {
                semaphore.acquire();
                System.out.println("Thread 1 acquire success");
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread 1 releases");
            semaphore.release();
        });

        Thread thread2 = new Thread(()->{
            System.out.println("Thread 2 Start");
            try {
                semaphore.acquire();
                System.out.println("Thread 2 acquire success");
                thread1.start();

                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread 2 releases");
            semaphore.release();
        });

        Thread thread3 = new Thread(()->{
            System.out.println("Thread 3 Start");
            try {
                semaphore.acquire();
                System.out.println("Thread 3 acquire success");
                thread2.start();
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread 3 releases");
            semaphore.release();
        });

        thread3.start();

        while(!thread1.isAlive()){

        }

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected static void reentrantTest(){
        Semaphore semaphore = new Semaphore(2);

        Thread thread1 = new Thread(()->{
            System.out.println("Thread 1 Start");
            try {
                semaphore.acquire();
                System.out.println("Thread 1 acquire success");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            semaphore.release();

        });

        Thread thread2 = new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("Thread 2 first acuqire success");
                semaphore.acquire();
                System.out.println("Thread 2 re-acquire success");

                thread1.start();

                Thread.sleep(10000L);
                semaphore.release();

                System.out.println("Thread 2 first releases");

                Thread.sleep(10000L);
                semaphore.release();

                System.out.println("Thread 2 second release");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread2.start();



        while(!thread1.isAlive()){

        }

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(thread2.isAlive()){
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    protected static void releaseWithoutAcquire(){
        Semaphore semaphore = new Semaphore(1);

        Thread thread1 = new Thread(()->{
            System.out.println("Thread 1 start");
            try {
                semaphore.acquire();
                System.out.println("Thread 1 acquires success");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(()->{
            System.out.println("Thread 2 Start");
            try {
                thread1.start();

                Thread.sleep(10000L);

                System.out.println("Thread 2 release without acquiring");
                semaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(()->{
            System.out.println("Thread 3 Start");
            try {
                semaphore.acquire();
                System.out.println("Thread 3 acquires success");

                thread2.start();
                Thread.sleep(100000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread3.start();
    }
}
