package com.insanexs.mess.concurrency.blockingqueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName LinkedBlockingQueueDeadLockTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/2/10 11:15 AM
 */
public class LinkedBlockingQueueDeadLockTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Integer> lbq = new LinkedBlockingDeque<>(5);
        lbq.put(1);
        lbq.put(2);
        lbq.put(3);
        lbq.put(4);
        lbq.put(5);

        Thread putThread = new Thread(()->{
            try {

                System.out.println("put Thread start");
                // blocking
                lbq.put(6);
                System.out.println("after put");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread removeThread = new Thread(()->{
            System.out.println("Remove Thread start");
            lbq.remove(1);
            System.out.println("after remove");
        });

        putThread.start();

        removeThread.start();
    }
}
