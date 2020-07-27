package com.atguigu.day06

object Scala58_Method1 {

    def main(args: Array[String]): Unit = {
      //apply主要用于构建对象
      val user=new User16

      //apply方法一般用于object伴生对象中的构建方法
      //伴生对象可以访问伴生类的私有属性和方法
      //Scala会自动识别apply方法,apply可省略


      val user1=User16("zhangsan")
      println(user1)
  }
}

class User16{
}

object User16{
  //apply方法要想被编译器自动识别，不能省略小括号
  def apply(): User16 = new User16()

  def apply(name:String): User16 = {
    println("name :"+name)
    new User16
  }
}