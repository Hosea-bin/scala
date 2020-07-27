package com.atguigu.day08

import scala.collection.mutable.ListBuffer

object Scala86_Collection_Seq2{

  def main(args: Array[String]): Unit = {

    val buffer: ListBuffer[Int] = ListBuffer(1,2,3,4)

    buffer.remove(0)  //数组索引移除
    println(buffer)

    println(buffer.updated(1,9))
  }
}
