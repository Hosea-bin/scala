package com.atguigu.day05

object Scala52_Class3 {

  def main(args: Array[String]): Unit = {

    //Scala中class可以继承父类
    //

    //Scala当省略类型，编译器会自动构建对象的类型进行推断
    //val user = new user1
    //使用多态需显示声明
    val user:parent = new user1
    println(user)
    }
 }

class parent{

}
class user1 extends parent{

}
