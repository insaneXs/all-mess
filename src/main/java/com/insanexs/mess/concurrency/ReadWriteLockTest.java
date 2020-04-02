package com.insanexs.mess.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/2/5 10:22 AM
 */
public class ReadWriteLockTest {

    public static void main(String[] args){
        System.out.println("**********************downgrade lock test");

        donwgradeLock();

        System.out.println("**********************upgrade lock test");

        upgradeLock();

        System.out.println("*********************lock write lock while other thread acquires read lock");
        lockWriteLockWhileOtherThreadAcquiresReadLock();
    }

    //write lock => read lock (ReadWriteLock  Downgrades is ok)
    protected static void donwgradeLock(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        lock.writeLock().lock();
        System.out.println("WriteLock Locks Successful");

        lock.readLock().lock();
        System.out.println("ReadLock Locks Successful");

        lock.writeLock().unlock();
        System.out.println("WriteLock Unlocks Successful");

        lock.readLock().unlock();
        System.out.println("ReadLock Unlocks Successful");
    }

    //read lock => write lock(ReadWriteLock Can notUpgrade)
    protected static void upgradeLock(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        lock.readLock().lock();
        System.out.println("ReadLock Locks Successful");

        boolean ret = lock.writeLock().tryLock();
        System.out.println("WriteLock Locks " + (ret ? "Successes" : "failed"));

        if(ret){
            lock.writeLock().unlock();
            System.out.println("WriteLock Unlocks Successful");
        }


        lock.readLock().unlock();
        System.out.println("ReadLock Unlocks Successful");
    }

    protected static void lockWriteLockWhileOtherThreadAcquiresReadLock(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        Thread thread1 = new Thread(()->{
            System.out.println("Thread 1 Starts");

            boolean ret = lock.writeLock().tryLock();
            System.out.println("Thread 1 tryLock WriteLock Result:" + ret);
        });

        Thread thread2 = new Thread(()->{
            System.out.println("Thread 2 Starts");

            lock.readLock().lock();
            System.out.println("Thread 2 lock readlock successes");

            thread1.start();
        });

        thread2.start();
    }
}
