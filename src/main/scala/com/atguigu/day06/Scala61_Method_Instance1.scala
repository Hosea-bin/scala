package com.atguigu.day06

object Scala61_Method_Instance1 {

    def main(args: Array[String]): Unit = {

      //主构造方法：类名后的构造方法，完成类的初始化
      //辅助构造方法，声明方式：def this()={}
                  //在使用辅助构造方法时，必须直接或者间接调用主构造方法
      //也存在方法的重载
    val user = new User()
      val user1 = new User("zhangsan")
      val user2 = new User("zhangsan",30)

      println(user)
      println(user1)
      println(user2)
  }
  class User(){
    println("XXXXXX")
    def this(name:String)={
      this()
    }
    def this(name:String,age:Int)={
      this(name)
    }
  }
}

