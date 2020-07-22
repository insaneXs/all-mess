package com.insanexs.mess.objectheader;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-07-22
 */
public class Foo {

    private Monitor lock = new Monitor();


    public void sync(){
        synchronized (lock){
            System.out.println("------------in sync()-------------");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
    }


    public void syncAndSleep() throws InterruptedException {
        synchronized (lock){
            System.out.println("------------take time sync()-------------");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());

            Thread.sleep(5000);

        }
    }

    public void printLockObjectHeader(){
        System.out.println("Thread:" + Thread.currentThread().getName() + ";" +ClassLayout.parseInstance(lock).toPrintable());
    }

    public void calculateHashAndPrint(){
        System.out.println("Calculate Hash:" +Integer.toHexString(lock.hashCode()));

        System.out.println("After invoke hashcode, print Object again");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }

}
