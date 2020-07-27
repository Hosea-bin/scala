package com.atguigu.day08

import scala.collection.mutable.ListBuffer

object Scala87_Collection_Set{

  def main(args: Array[String]): Unit = {

    //Set 无序，不可重复

    val set: Set[Int] = Set(1,2,3,4)
    val set1: Set[Int] = set + 5
    val value: Any =  set+6
    println(set eq set1)
    println(set)
    println(set1)

    val set2: Set[Int] = Set(1,2,3,4,5,6,7,7,8)
    println(set2)
  }
}
