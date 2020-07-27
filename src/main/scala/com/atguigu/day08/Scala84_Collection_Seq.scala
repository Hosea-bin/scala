package com.atguigu.day08

import scala.collection.mutable.ArrayBuffer

object Scala84_Collection_Seq {

  def main(args: Array[String]): Unit = {
   //默认情况scala集合不可变，immutable
    //list集合构造对象，需采用apply方法
    val list:List[Int]=List(1,2,3,4)

    //数据处理
    val newList: List[Int] = list :+ 5
    val newList1: List[Int] = 5 +: list
    println(list eq newList)
    println(newList)
    println(newList1)
    println(list)

    println(list.mkString(","))
    list.foreach(println)
  }
}
