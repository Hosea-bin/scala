package com.atguigu.day06

object Scala64_Method_Instance4 {

    def main(args: Array[String]): Unit = {
      //父类构造方法需要传参，则子类构建父类对象时也应该传参
      val user = new User  //先构建父类
    val emp = new Emp("wangwu")  //子类传递给父类，赋值
  }
  class Person(name:String){
    println("name : "+name)
  }
  class User extends  Person(name="lisi"){
    println("user..")
  }
  class Emp(name:String)extends Person(name){
    println("emp")
  }
}

