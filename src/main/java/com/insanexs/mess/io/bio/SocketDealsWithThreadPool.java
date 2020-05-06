package com.insanexs.mess.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName SocketDealsWithThreadPool
 * @Description TODO
 * @Author xieshang
 * @Date 2020/4/9 4:05 下午
 */
public class SocketDealsWithThreadPool {

    public static void main(String[] args){


    }

    public static void createServerThread(){
        Thread server = new Thread(() ->{
            try {
                //BACKLOG 属性测试
                ServerSocket serverSocket = new ServerSocket(10086, 2);

                while(true){
                    Socket socket = serverSocket.accept();
                    InputStream is = socket.getInputStream();
                    byte[] bytes = new byte[1024];

                    int readByte;
                    while((readByte = is.read(bytes)) > -1){
                        System.out.println(bytes);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "ServerThread");
        server.start();
    }

    public static void createClientThread(){
        Thread client = new Thread(() ->{
            try {
                Socket socket = new Socket("127.0.0.1", 10086);
                System.out.println(socket.getLocalPort());

                OutputStream os = socket.getOutputStream();
                while(true){
                    Thread.sleep(1000L);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ClientThread");
        client.start();
    }
}
