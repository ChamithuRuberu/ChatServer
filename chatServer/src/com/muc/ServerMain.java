package com.muc;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerMain {
    public static void main(String[] args) {
        int port =8818;
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            while (true) {
                System.out.println("Accept About Client Connection..");
                Socket ClientSocket = serverSocket.accept();
                System.out.println("Client Connection.."+ClientSocket);
                Thread thread =new Thread(){
                    @Override
                    public void run() {
                        try {
                            extrhandleClientSocket(ClientSocket);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
                    thread.start();
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    private static void extrhandleClientSocket(Socket ClientSocket) throws IOException, InterruptedException {
        OutputStream outputStream = ClientSocket.getOutputStream();
        for (int i=0;i<10;i++){
            outputStream.write(("time is now"+ new Date()+"\n").getBytes());
            Thread.sleep(1000);
        }
        ClientSocket.close();
    }
}
