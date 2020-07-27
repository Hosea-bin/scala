package com.atguigu.day10

object Scala122_Transform2{
  def main(args: Array[String]): Unit = {
    implicit def transform(user:User):Parent={
      new Parent()
    }
    //动态混入
    //将函数声明前增加implicit关键字，可以由编译器自动识别和自动调用
    //完成类型的转换，并扩展功能

    val user =new User();
    user.inserUser()
    user.updateUser()
  }
  class Parent{
    def updateUser()={
      println("update user...")
    }
  }

  class User{
    def inserUser():Unit={
      println("insert user....")
    }
  }
}
