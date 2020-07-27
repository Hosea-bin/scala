package com.atguigu.day02

object Scala07_Operator1 {
  def main(args: Array[String]): Unit = {
    val a = new String("aaa")
    val b = new String("aaa")
    //Scala中String的==其实是equals,java中==比较的是内存地址
    println(a==b)
    println(a.equals(b))
    //Scala中eq用于比较字符串的内存
    println(a eq(b))

  }

}
