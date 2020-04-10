package com.insanexs.mess.threads.threadpoolexecutor;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import java.util.concurrent.*;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-04-01
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService es = new ThreadPoolExecutor(2, 10, 10000, TimeUnit.MILLISECONDS, blockingQueue, new MyThreadFactory());

        Thread.sleep(1500);

        for(int i=1; i<=10; i++){
            final int idx = i;
            es.submit(() ->{
               System.out.println("- Execute Task " + idx + "");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(200);
        }

    }

    static class MyThreadFactory implements ThreadFactory{
        private int i = 0;

        @Override
        public Thread newThread(Runnable r) {
            System.out.println("+ Create Thread Index:" + i++);
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        }
    }
}
