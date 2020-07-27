package com.atguigu.day02

object Scala08_Operator2 {
  def main(args: Array[String]): Unit = {
   val user1=null
    val user2 =new User1()
    println(user1==user2)
   //println(user1.equals(user2))//未作非空判断
    println(user1 eq(user2))
      //Scala中eq一般比较对象的内存地址
    //==比较内容，类似equals方法，并且进行了非空判断


  }

}
