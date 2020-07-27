package com.atguigu.day06

import scala.beans.BeanProperty

object Scala54_Field {

    def main(args: Array[String]): Unit = {
    //class可以声明在其他任何地方
      val user = new User09()
      user.name="wangwu"

      user.name_$eq("lisi")
      println(user.name)
        //声明的属性不仅只有属性，还提供了get和set方法

      //使用val,属性被final修饰，只提供get方法

      user.setName("ww")
      println(user.getName)

      user.setAge(22)
    }
  //内部类
  class User09{
    @BeanProperty
    var name : String = _
    @BeanProperty
    var age : Int = _
    val sex : String = "男"
  }
}

