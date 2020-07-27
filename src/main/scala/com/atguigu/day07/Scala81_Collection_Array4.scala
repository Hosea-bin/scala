package com.atguigu.day07

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Scala81_Collection_Array4 {
  def main(args: Array[String]): Unit = {
   val array = Array(2,3,4)

   val buffer: mutable.Buffer[Int] = array.toBuffer
   buffer.append(1,2,3)
   println(buffer)
   val array2: ArrayBuffer[Int] = ArrayBuffer(1,2,3,4)
   val array3: Array[Int] = array2.toArray
    val a: Array[Int] = array3 :+ 5
    println(a.mkString(","))
  }
}
