package com.atguigu.day07

import scala.collection.mutable.ArrayBuffer

object Scala79_Collection_Array2 {
  def main(args: Array[String]): Unit = {
    //可变
    val array = new ArrayBuffer[String]()

    array.append("a")
    array.append("c" +","+
      "v" +
      "d")
    println(array)
    println(array.mkString(","))
    for(s <-array){
      println("s ="+s)
    }
  }
}
