package com.insanexs.mess.threads.park;

import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-03-31
 */
public class ParkThreadInterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            LockSupport.park();

            System.out.println("After Park");
        });

        thread.start();

        int count = 0;
        boolean ret;
        do{
            Thread.sleep(1000L);

            ret = Math.random() >= 0.9d;
            System.out.println("Prepare to interrupt Thread ?" + ret);
            if(ret){
                thread.interrupt();
            }
        }while(!ret);

    }
}
