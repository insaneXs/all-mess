package com.insanexs.mess.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-06-13
 */
public class BasicNioServer {
    private int port;

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private Thread thread;

    public BasicNioServer(int port) throws IOException {
        init(port);
    }

    private void init(int port) throws IOException {
        this.port = port;
        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        //non-block mode
        serverSocketChannel.configureBlocking(false);
    }

    private void work() throws IOException {
        serverSocketChannel.bind(new InetSocketAddress(port));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(!Thread.interrupted()){
            //block until at least one of I/O operations bound to this selector are ready
            selector.select();
            Set<SelectionKey> selectionKeys =  selector.selectedKeys();

            Iterator<SelectionKey> iter = selectionKeys.iterator();

            while(iter.hasNext()){
                SelectionKey selectionKey = iter.next();
                SelectableChannel channel = selectionKey.channel();


                if(selectionKey.isAcceptable()){
                    System.out.println("Connection Establish");
                    SocketChannel socketChannel = ((ServerSocketChannel) channel).accept();
                    socketChannel.configureBlocking(false);
                    //register new SocketChannel to Selector
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }

                if(selectionKey.isReadable()){
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    if(-1 !=((SocketChannel)channel).read(buffer)){

                        Charset charset = Charset.forName("UTF-8");
                        CharsetDecoder decoder = charset.newDecoder();
                        buffer.flip();
                        CharBuffer charBuffer = decoder.decode(buffer);

                        System.out.println("Receive message:" + charBuffer.toString().trim());

                        buffer.flip();
                        buffer.put(charBuffer.toString().getBytes());
                        buffer.flip();
                        ((SocketChannel) channel).write(buffer);
                    }else{
                        selectionKey.cancel();
                        channel.close();
                    }

                }

                selectionKeys.remove(selectionKey);
            }
        }
    }

    public synchronized void start(){
        if(thread == null){
            thread = new Thread(()->{
                try {
                    work();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws IOException {
        BasicNioServer server = new BasicNioServer(6666);
        server.start();
    }
}
