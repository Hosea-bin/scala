package com.atguigu.day02;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
//网络中直接传输不了对象，需将对象形成序列化，及反序列化
public class User4_NetClinet {
    public static void main(String[] args) throws IOException {
        //客户端
        Socket localhost = new Socket("localhost", 9999);
        //Output
        User4 user4 = new User4();
        user4.name="zhangsan";
        //序列化对象
        ObjectOutputStream object=new ObjectOutputStream(localhost.getOutputStream());
        object.writeObject(user4);
        System.out.println("客户端发送对象的数据");
        localhost.close();

    }
}
