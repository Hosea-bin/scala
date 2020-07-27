package com.atguigu.day06

object Scala62_Method_Instance2 {

    def main(args: Array[String]): Unit = {

      //从外部将数据传递到对象属性中

    val user=new User("lisi")
      println(user.username)
      //Scala可以使用var,val将构造参数当成类的属性来用
      val emp=new Emp("lili")
      println(emp.name)

  }
  class User(name:String){
    //name :函数参数，局部变量，外部无法访问
    //username : 类的属性，局部变量，外部可以fw
    val username : String =name
  }
  class Emp(val name:String){

  }
}

