package com.insanexs.mess.objectheader;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-07-22
 */
public class SynchronizedUpgradeTest {
    static Foo foo = new Foo();

    public static void main(String[] args) throws InterruptedException {
//        hashCodeTest();
//        biasedLock();

//        biasedLockInvalidAfterCalculate();

//        biasedLockUpgradeToLightLock();

        lightLockToWeightLock();
    }

    /**
     * JVM OPTIONS: -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
     */
    protected static void hashCodeTest(){
        foo.printLockObjectHeader();
        foo.calculateHashAndPrint();
    }

    /**
     * JVM OPTIONS: -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
     */
    protected static void biasedLock(){

        foo.printLockObjectHeader();

        foo.sync();

        System.out.println("Exit sync()");
        foo.printLockObjectHeader();
    }

    /**
     * JVM OPTIONS: -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
     */
    protected static void biasedLockInvalidAfterCalculate(){
        foo.printLockObjectHeader();
        foo.calculateHashAndPrint();
        foo.sync();
        System.out.println("out sync()");
        foo.printLockObjectHeader();
    }

    /**
     * JVM OPTIONS: -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
     */
    protected static void biasedLockUpgradeToLightLock(){
        foo.printLockObjectHeader();
        foo.sync();
        System.out.println("out sync()");
        foo.printLockObjectHeader();

        System.out.println("---Another Thread Use Biased Lock");
        Thread thread = new Thread(()->{
            foo.sync();
            System.out.println("---Another Thread Out sync");
            foo.printLockObjectHeader();
        });
        thread.start();
    }
    /**
     * JVM OPTIONS: -XX:UseBiasedLocking -XX:BiasedLockingStartupDelay=10
     */
    protected static void lightLockToWeightLock() throws InterruptedException {
        foo.printLockObjectHeader();

        new Thread(()->{
            try {
                foo.syncAndSleep();
                foo.printLockObjectHeader();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000L);

        foo.sync();
        foo.printLockObjectHeader();
    }
}
