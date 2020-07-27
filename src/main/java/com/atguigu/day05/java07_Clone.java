package com.atguigu.day05;

public class java07_Clone {
    public static void main(String[] args) throws Exception{
       AAA aaa = new AAA();
       //方法的提供者：package com.atguigu.day05.AAA
        //方法的调用者：package com.atguigu.day05.java07_Clone
        //同一个包
       aaa.clone();

        Object bbb = new AAA();
        //方法的提供者：java.lang.Object
        //方法的调用者：package com.atguigu.day05.java07_Clone
       // bbb.clone();
    }
}
class AAA{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


