package com.atguigu.day02;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class User4_NetServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //服务器
        ServerSocket server = new ServerSocket(9999);

        final Socket client = server.accept();
        //input反序列化
        ObjectInputStream objInput = new ObjectInputStream(client.getInputStream());
        User4 user4 = (User4) objInput.readObject();
        System.out.println("服务器读取的数据为 " + user4.name);
    }
}
