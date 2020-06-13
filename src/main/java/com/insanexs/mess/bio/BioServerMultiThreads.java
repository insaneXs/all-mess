package com.insanexs.mess.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-06-13
 */
public class BioServerMultiThreads {

    private int port;

    private ServerSocket serverSocket;

    private Thread mainThread;

    public BioServerMultiThreads(int port) throws IOException {
        init(port);
    }

    private void init(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));

    }

    private void work() throws IOException {
        while(!Thread.interrupted()){
            Socket socket = serverSocket.accept();
            dispatch(socket);
        }
    }

    private void dispatch(Socket socket){
        new Thread(new Handler(socket)).start();
    }

    public synchronized void start(){
        if(mainThread == null){
            mainThread = new Thread(() ->{
                try {
                    work();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            mainThread.start();
        }
    }

    class Handler implements Runnable{

        private Socket socket;

        private byte[] buffer = new byte[1024];

        private static final String DEFAULT_CHARSET = "UTF-8";

        public Handler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                while(!Thread.interrupted() && socket.getInputStream().read(buffer) != -1){
                    String message = new String(buffer, DEFAULT_CHARSET).trim();
                    System.out.println("Receive message from client:" + message);
                    socket.getOutputStream().write(message.getBytes());
                }
            } catch (IOException e) {
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        }
    }


    public static void main(String[] args) throws IOException {
        BioServerMultiThreads server = new BioServerMultiThreads(6666);

        server.start();
    }

}
