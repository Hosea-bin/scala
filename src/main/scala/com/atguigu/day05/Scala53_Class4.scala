package com.atguigu.day05

object Scala53_Class4 {

  def main(args: Array[String]): Unit = {
    //可以通过对象在外部访问
    val user=new User03
    user.name="zhangsan"
    println("name =" +user.name)

    //变量应显示初始化，若要跟Java一样，采用特殊符号下划线

    //若用val来声明。则初始值不能使用下划线，需显示赋值
    //使用val声明的属性也不能该
  }
}

class User03{
  var name:String= _
  var age : Int= _
  val sex : String = "男"
}