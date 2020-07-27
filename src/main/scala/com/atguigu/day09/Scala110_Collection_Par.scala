package com.atguigu.day09

import scala.collection.parallel.immutable.ParSeq
import scala.collection.{immutable, mutable}

object Scala110_Collection_Par {

  def main(args: Array[String]): Unit = {
  //并行集合，用于多核环境的并行计算
//    val result1: immutable.IndexedSeq[String] = (0 to 100).map(x => Thread.currentThread.getName)
//    println(result1)
    val result: ParSeq[String] = ((0 to 100)).par.map(x => Thread.currentThread.getName)
    println(result)

    //解决线程安全问题
    //1.线程串行，不推荐
    //2.让对象存储的内存不共享
    //3.让对象不共享，多例对象
    //4.调用对象的方法不会出现线程安全问题，因为压栈操作是独享操作（栈空间不一样）
    //5.只是访问属性，而不是修改属性，也不会出现线程安全问题
  }
}
