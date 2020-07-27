package com.atguigu.day08

import scala.collection.mutable

object Scala94_Collection_Method{

  def main(args: Array[String]): Unit = {

   //常用方法
     val list = List(1,2,3,4)
    //获取集合长度
    println(list.length)
    println(list.size)

    //遍历数据
    list.foreach(println)
    println(list.mkString(","))
    val iterator: Iterator[Int] = list.iterator
    while (iterator.hasNext){
      println(iterator.next())
    }

    println(list.isEmpty)

    println(list.sum)
    println(list.max)
    println(list.min)
    println(list.product)
  }
}
