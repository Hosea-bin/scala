package com.atguigu.day05;

public class java05_Access {
    public static void main(String[] args) throws Exception{
       User05 user05= new User05();
       //Object提供的方法：Private,public,protected
        // . 的作用从属关系
        //真正的调用者是：com.atguigu.day05.java05_Access
        user05.name="lisi";
    }
}
//声明一个类，默认继承Object类，同时继承Object的方法
class User05{
    public String name = null;
}