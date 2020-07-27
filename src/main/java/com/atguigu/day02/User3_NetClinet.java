package com.atguigu.day02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class User3_NetClinet {
    public static void main(String[] args) throws IOException {
        //客户端
        Socket localhost = new Socket("localhost", 9999);
        //Output
        localhost.getOutputStream().write(55);
        System.out.println("客户端发送的数据");
        localhost.close();

    }
}
