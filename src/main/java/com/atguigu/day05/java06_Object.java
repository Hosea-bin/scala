package com.atguigu.day05;

import com.atguigu.day02.User;

public class java06_Object {
    public static void main(String[] args) throws Exception{
        User06 user06= new User06();
        User06 user07= new User06();
        user06.test();
        user06.superTest();

        System.out.println(user06.hashCode());
        System.out.println(user06.hashCode());

        System.out.println(user07.hashCode());
        System.out.println(user07.hashCode());
    }
}

//声明一个类，默认继承Object类，同时继承Object的方法
class Person06{
    public void test(){
        System.out.println("person");
    }
}
class User06 extends Person06{
    public void test(){
        System.out.println("user");
    }
    public void superTest(){
        super.test();
    }
    public int superHashCode(){
        return super.hashCode();
    }
}
