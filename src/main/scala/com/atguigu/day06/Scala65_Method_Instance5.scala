package com.atguigu.day06

object Scala65_Method_Instance5 {

    def main(args: Array[String]): Unit = {

      //先构造父类，再构造子类
     val user = new User("wangwu")
      //new User("wangwu")
  }

  class Person(name:String){
    println("aaaaa")
    def this()={
      this("lisi")
      println("bbbb")
    }
  }

  class User() extends Person("zhangsan"){
    println("cccccc")
    def this(name:String)={
      this()
      println("ddddd")
    }
  }
}

