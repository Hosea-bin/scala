package com.atguigu.day08

import scala.collection.mutable

object Scala91_Collection_Map2{

  def main(args: Array[String]): Unit = {

   //map -映射 - 可变
    val map: mutable.Map[String, Int] = mutable.Map("a"->3,"c"->2,"b"->2)

    val maybeInt: Option[Int] = map.get("a")
    println(maybeInt)
    println(maybeInt.get)
    println(maybeInt.getOrElse(-1))

    val i: Int = map.getOrElse("d",-1)
    println(i)
  }
}
