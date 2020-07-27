package com.atguigu.day01;

import java.lang.reflect.Field;

public class HelloScala {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s = " name ";
//        s.trim();
//        System.out.println(s);
        Class<? extends String> aClass = s.getClass();
        Field f = aClass.getDeclaredField("value");
        f.setAccessible(true);
        char[] o = (char[])f.get(s);
        o[2]='d';
        System.out.println(s);

    }
}
