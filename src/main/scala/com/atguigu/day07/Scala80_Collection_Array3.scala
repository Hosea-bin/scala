package com.atguigu.day07

import scala.collection.mutable.ArrayBuffer

object Scala80_Collection_Array3 {
  def main(args: Array[String]): Unit = {
   //数组可变
    val array: ArrayBuffer[Int] = ArrayBuffer(1,2,3,4)

    array.append(5)
    array.append(6)

    array.insert(1, 8,9,10)

    array(2)=7
    array.update(1,5)
    println(array)
    println(array.mkString(","))
  }
}
