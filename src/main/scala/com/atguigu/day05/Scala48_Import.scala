package com.atguigu.day05


object Scala48_Import {

    def main(args: Array[String]): Unit = {
      //导类
      //import static  静态导入，导入类的静态属性和方法
      //1、import 可以声明在任意位置
      //val date = new Date("d")

      //2、scala默认导入的类是  java.lang包中的类，scala中的类，predef(类似java中的静态导入)

      //3.Scala中的import可以导包
      import java.sql
     //val date = new sql.Date();

      //4.导入包中所有的类，使用下划线代替java中的星号
      //import java.util.*;
      import java.util._

      //5.可以在一行中导入同一个包中的多个类

      //使用import关键字将指定类起别名
      import java.util.{Date=>UtilDate}
      new UtilDate()
      type UtilDate=java.util.Date
      val a : UtilDate=new UtilDate()

      //Scala默认是按照包的相对路径导入的
      //如果不想使用相对路径，可以采用特殊的路径（root)访问
      println(new _root_.java.util.HashMap())

    }
  }

