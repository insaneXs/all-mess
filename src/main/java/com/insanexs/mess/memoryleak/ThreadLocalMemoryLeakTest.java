package com.insanexs.mess.memoryleak;

import java.io.IOException;

/**
 * @ClassName ThreadLocalMemoryLeakTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/5/9 7:04 下午
 */
public class ThreadLocalMemoryLeakTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        Thread thread = new Thread(()->{
            for(int i=0; i<1000; i++){
                ThreadLocal<BigObject> tl = new ThreadLocal();
                tl.set(new BigObject());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //tl.remove();
            }
            System.out.println("Thread Quit");
            System.gc();
        });
        thread.start();

        System.in.read();
    }

    static class BigObject{
        Integer[] arrs = new Integer[1024 * 1024];
    }
}
