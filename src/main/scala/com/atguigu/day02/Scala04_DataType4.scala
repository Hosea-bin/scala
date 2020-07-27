package com.atguigu.day02

import java.util
object Scala04_DataType4{
  def main(args: Array[String]): Unit = {
    //自动类型转换
    //Scala可通过隐式转换将Byte类型转换为Short类型
    val a : Byte =10
    val b : Short = a
    val i : Int = b
    val l : Long = i

    //将精度大的类型转换为精度小的类型
    //scala方法转换
    val d : Byte = l.toByte
  }

}
