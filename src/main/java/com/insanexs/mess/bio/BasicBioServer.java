package com.insanexs.mess.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: xieshang
 * @Description: Basic Bio Server:
 * @Date: Create at 2020-06-13
 */
public class BasicBioServer {
    private int port;
    private ServerSocket serverSocket;

    private Thread workerThread;

    private byte[] buffer = new byte[1024];

    private byte[] writeByte = "Echo".getBytes();

    private static final String DEFAULT_CHARSET = "UTF-8";

    public BasicBioServer(int port) throws IOException {
        init(port);
    }


    protected void work() throws IOException {
        while(!Thread.interrupted()){
            Socket socket = serverSocket.accept();
            System.out.println("Connection Establish");

            //Block if no input is yet available.
            while(socket.getInputStream().read(buffer) != -1){

                String message = new String(buffer, DEFAULT_CHARSET).trim();
                System.out.println("Receive message from client:" + message);

                //Write message
                socket.getOutputStream().write(message.getBytes());
                socket.getOutputStream().flush();
                System.out.println("Message Wrote");
                buffer = new byte[1024];
            }

            if(socket != null){
                socket.close();
            }
            System.out.println("Connection Disconnect");
        }
    }

    private void init(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket();
    }

    public synchronized void start(){
        if(workerThread == null){
            workerThread = new Thread(()->{
                try {
                    //bind local port, start listening
                    serverSocket.bind(new InetSocketAddress(port));
                    work();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            workerThread.start();
        }
    }

    public static void main(String[] args) throws IOException {
        BasicBioServer server = new BasicBioServer(6666);
        server.start();

//        System.in.read();
    }


}
