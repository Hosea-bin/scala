package com.atguigu.day10

object Scala126_Transform6{
  def main(args: Array[String]): Unit = {

  def transform(implicit d:Double)={
    d.toInt
  }
    implicit val dd:Double=2.0

    val result: Int =transform
    println(result)
  }
}
