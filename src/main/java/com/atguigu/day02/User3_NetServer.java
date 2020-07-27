package com.atguigu.day02;

import com.atguiu.Scala06_Identifier;
import com.atguiu.Scala06_Identifier$;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class User3_NetServer {
    public static void main(String[] args) throws IOException {
        //服务器
        ServerSocket server = new ServerSocket(9999);
        while (true) {
            final Socket client = server.accept();
        new Thread(new Runnable() {
            public void run() {
                try {
                    int data = client.getInputStream().read();
                    System.out.println("服务器读取的数据为 "+data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        }
    }
}
