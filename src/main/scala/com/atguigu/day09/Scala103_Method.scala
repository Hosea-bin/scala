package com.atguigu.day09

import scala.collection.mutable

object Scala103_Method {

  def main(args: Array[String]): Unit = {
  val list = List(1,2,3,4)
  val list1 = List(2,3,4,5)
//    println(list.iterator)
//    for (elem <- list) {println(elem)}
    println(list.tail)
    println(list.tails)
    println(list.union(list1))
    println(list.union(list))
  }
}
