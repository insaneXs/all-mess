package com.insanexs.mess.threadgc;

/**
 * @ClassName ThreadGCTest
 * @Description TODO
 * @Author insaneXs
 * @Date 2020/8/9
 */
public class ThreadGCTest {


    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<1000; i++){
            ThreadHolder holder = new ThreadHolder();
            Thread.sleep(100);
        }
    }


    static class ThreadHolder{
        //10M
        char[] bigdata = new char[1024 * 1024];

        Thread thread;

        public ThreadHolder(){
            thread = new Thread(()->{
                //调用外部的方法 导致ThreadHolder无法被释放
                this.run();
            });
            thread.start();
        }

        protected void run(){
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void finalize() throws Throwable {
            super.finalize();
            System.out.println("Thread Holder finalized");
        }
    }
}
