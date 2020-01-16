package com.insanexs.mess.threads.interrupt;

/**
 * @ClassName ThreadInterruptTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/1/15 5:18 PM
 */
public class ThreadInterruptTest {


    public static void main(String[] args){

        testInterrupt();

        /* -----线程中断状态抛异常情况测试------ */

        //INTERRUPT RUNNABLE THREAD WILL NOT THROW INTERRUPT EXCEPTION
        System.out.println("==============TEST INTERRUPT RUNNABLE================");
        testInterruptRunnable();

        //INTERRUPT BLOCKING THREAD WILL NOT THROW INTERRUPT EXCEPTION
        System.out.println("==============TEST INTERRUPT AFTER BLOCKING================");
        testInterruptAfterBlockingThread();

        System.out.println("=============TEST INTERRUPT BEFROE BLOCKING================");
        testInterruptBeforeBlockingThread();

        //INTERRUPT WAIITNG THREAD WILL THROW INTERRUPT EXCEPTION
        System.out.println("==============TEST INTERRUPT WAITING================");
        testInterruptWaitingThread();

    }

    protected static void testInterrupt(){

        Thread runnableThread = new Thread(()->{
            for(int i=0; i<1000; i++){
               mockDoSomething();
           }

           // Thead.interrupted()会返回线程的中断的标识位，并清空标识位
            System.out.println("invkoe Thread.interrupted(), And the result is "+ Thread.interrupted());
            System.out.println("After Thread.interrupted(), Thread Interrupt flag is " + Thread.currentThread().isInterrupted());
        });

        runnableThread.start();


        System.out.println("Before Interrupt, Thread Interrupt flag is " + runnableThread.isInterrupted());
        //interrupt()方法会设置线程中断的标识位
        runnableThread.interrupt();

        //isInterrupted()方法会返回当前中断的标识位
        System.out.println("After Interrupt, Thread Interrupt flag is " + runnableThread.isInterrupted());

        try {
            runnableThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    protected static void testInterruptRunnable() {
        Thread runnableThread = new Thread(()->{
            for(int i=0; i<1000; i++) {
            }
            System.out.println("Runnable Thread Finish The Job");
        }, "Runnable Thread");

        interruptThread(runnableThread);
        runnableThread.start();

        try {
            runnableThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected static void testInterruptAfterBlockingThread(){
        try {
            Object lock = new Object();
            Thread blockedThread = new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " starts");
//                try {
                    synchronized (lock){

                    }
//                } catch (InterruptedException e){
//                    System.out.println(Thread.currentThread().getName() + "catches intterrupted exception");
//                }

            }, "blocked Thread");


            Thread occupiedLockedThread = new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " starts");
                synchronized (lock){

                    try {
                        blockedThread.start();
                        mockDoSomething();
                        interruptThread(blockedThread);
                        Thread.sleep(10000l);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " catch intterrupted exception");
                    }
                }
            }, "Occupied Locked Thread ");

            occupiedLockedThread.start();
            occupiedLockedThread.join();

        }catch (Exception e){
            System.out.println("test Interrupt Blocking Thread Catch Exception:" + e);
        }
    }

    protected static void testInterruptBeforeBlockingThread(){
        try {
            Object lock = new Object();
            Thread blockedThread = new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " starts");
//                try {
                synchronized (lock){

                }
//                } catch (InterruptedException e){
//                    System.out.println(Thread.currentThread().getName() + "catches intterrupted exception");
//                }

            }, "blocked Thread");


            Thread occupiedLockedThread = new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " starts");
                synchronized (lock){

                    try {
                        interruptThread(blockedThread);
                        blockedThread.start();

                        Thread.sleep(10000l);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " catch intterrupted exception");
                    }
                }
            }, "Occupied Locked Thread ");

            occupiedLockedThread.start();
            occupiedLockedThread.join();

        }catch (Exception e){
            System.out.println("test Interrupt Blocking Thread Catch Exception:" + e);
        }
    }

    protected static void testInterruptWaitingThread(){
        try {
            Thread sleepThread = new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + " starts");
                try {
                    Thread.sleep(10000l);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " catch intterrupted exception");
                }
            }, "Sleeping Thread");

            Thread joinThread = new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + " starts");
                sleepThread.start();
                try {
                    mockDoSomething();
                    sleepThread.join();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " catch intterrupted exception");
                }
            },"Joining Thread");

            joinThread.start();
            interruptThread(joinThread);

            //LET MAIN THREAD WAIT UTIL JOIN THREAD FINISH
            joinThread.join();
        } catch (Exception e) {
            System.out.println("test Interrupt Waiting Thread Catch Exception:" + e);
        }
    }



    private static void interruptThread(Thread thread){
        thread.interrupt();
        System.out.println("Interrupt: " +  thread.getName() + ", and the state is " + thread.getState());
    }

    private static void mockDoSomething(){
        while(true){
            double randVal1 = Math.random();
            double randVal2 = Math.random();
//            System.out.println(randVal1 + ";" + randVal2);
            if(Math.abs(randVal1 - randVal2) < 0.001)
                break;
        }
    }
}
