package com.atguigu.day05

object Scala51_Class2 {

  def main(args: Array[String]): Unit = {
    //构建对象
    //class
    val user1=new User00
    println(user1)


    //object
    val user2=User01
    println(user2)

    //调用方法
    user1.test1()
    user2.test2()
    }

  }

class User00{
  def test1()={
    println("user...")
  }
}

object User01{
  def test2()={
    println("object...")
  }
}