package com.insanexs.mess.threads.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/1/18 11:21 AM
 */
public class ConditionTest{

    public static void main(String[] args){

        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Condition lock1Condition = lock1.newCondition();





        Thread signalThread = new Thread(() ->{

            lock1.lock();

            lock2.hasWaiters(lock1Condition);

            lock1.unlock();
        });

        Thread awaitThread = new Thread(()->{
            lock1.lock();

            signalThread.start();

            try {
                lock1Condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock1.unlock();
        });

        awaitThread.start();
    }
}
