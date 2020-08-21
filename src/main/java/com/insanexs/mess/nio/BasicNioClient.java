package com.insanexs.mess.nio;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @ClassName BasicNioClient
 * @Description TODO
 * @Author insaneXs
 * @Date 2020/8/8
 */
public class BasicNioClient {


    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<1000; i++){
            ClientWorker worker = new ClientWorker();
            worker.start();
            Thread.sleep(5000);
        }

    }

    static class ClientWorker extends Thread{
        Selector selector;

        @Override
        public void run() {
            try {
                selector = Selector.open();
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
                socketChannel.connect(new InetSocketAddress("localhost", 18080));
                Thread.sleep(1000);
                socketChannel.finishConnect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(true){
                try {
                    selector.select(1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
