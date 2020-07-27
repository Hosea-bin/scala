package com.atguigu.day10

object Scala121_Transform1{
  def main(args: Array[String]): Unit = {
    //Scala - 隐式转换
    implicit def transform(d:Double)={
      d.toInt
    }

    val i:Int=2.0
//    val i : Int = transform(2.0)
    println(i)
  }
}
