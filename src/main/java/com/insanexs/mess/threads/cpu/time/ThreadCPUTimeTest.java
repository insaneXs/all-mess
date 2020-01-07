package com.insanexs.mess.threads.cpu.time;

/**
 * @Author: insaneXs
 * @Description:
 * @Date: Create at 2020-01-07
 */
public class ThreadCPUTimeTest {

    public static void main(String[] args) {
        testBlockedThreadCPUTime();
    }

    protected static void testBlockedThreadCPUTime() {
        Object lock = new Object();

        Thread threadA = new Thread(() -> {
            synchronized (lock) {
                doSomethingElse();
            }
        }, "ThreadA: Blocked because of synchronized");

        Thread threadB = new Thread(() -> {
            synchronized (lock) {
                try {
                    threadA.start();
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ThreadB: With Monitor But Sleep");

        threadB.start();
        //Main Thread Executing Job
        for (int i = 0; i < 100000; i++) {
            doSomethingElse();
        }
    }

    private static void doSomethingElse() {
        double meanless = 0d;

        for (int i = 0; i < 10000; i++) {
            meanless += Math.random();
        }
    }
}
