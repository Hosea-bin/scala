package com.atguigu.day08

import scala.collection.mutable

object Scala88_Collection_Set2{

  def main(args: Array[String]): Unit = {

    //Set 可变集合
    val set: mutable.Set[Int] = mutable.Set(1,2,3,4)
    set.add(99)
    set.add(10)
    set.update(11,true)
    set.remove(1)
    val newSet: mutable.Set[Int] = set + 5 +7
    println(newSet)
    println(newSet eq set)
    //不会产生新的集合
    val set4: mutable.Set[Int] = set += 5
    println(set4)
    println(set4 eq set)
  }
}
