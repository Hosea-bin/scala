package com.atguigu.day02

object Scala14_Flow3 {
  def main(args: Array[String]): Unit = {

    val age = 20
    val s : String = if (age<20){
      "zhangsan"
    }else{
      "ll"
    }

    println(s)

    val ss = if (age<20) "zhangsan " else  "ll"
    println(ss)

    println(s);println(ss)
    }
}
