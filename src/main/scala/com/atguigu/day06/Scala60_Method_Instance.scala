package com.atguigu.day06

object Scala60_Method_Instance {

    def main(args: Array[String]): Unit = {

     //构造方法
      //Scala是一个完全面向对象，面向函数的语言
      //构造方法执行时，会完成类的主体内容的初始化
      //类名后面可以加括号
      val user = new User
      user.User()
  }
  class User(){

    println("user...")
    //普通方法
    def User()={
      println("user...。。。.")
    }
  }
}

