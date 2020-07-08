package com.insanexs.mess.concurrency.cpu.time;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: xieshang
 * @Description: 测试让线程等待的方式是 Sleep好还是Park好
 * @Date: Create at 2020-07-07
 */
public class SleepVSPark {

    public static void main(String[] args) throws InterruptedException {


        parkThread(1000);
    }

    protected static void sleepThread(int threadNum) throws InterruptedException {
        Thread.sleep(10 * 1000);
        System.out.println("TEST START");

        for(int i=0; i<threadNum; i++){
            new Thread(()->{
                long startTime = System.currentTimeMillis();

                while(System.currentTimeMillis() - startTime < 100 * 1000){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
    }

    protected static void parkThread(int threadNum) throws InterruptedException {
        Thread.sleep(10 * 1000);
        System.out.println("TEST START");

        for(int i=0; i<threadNum; i++){
            new Thread(()->{
                long startTime = System.currentTimeMillis();

                while(System.currentTimeMillis() - startTime < 100 * 1000){
                    LockSupport.parkNanos(10 * 1000000);
                }

            }).start();
        }
    }
}
