package com.atguigu.day08

import scala.collection.mutable

object Scala92_Collection_Tuple{

  def main(args: Array[String]): Unit = {

   //元组
    val data: (Int, String, Int, String) = (1,"zhangsan",30,"lili")
    //元组最多能放22个，不限类型
    println(data._1)
    println(data._2)
    println(data._3)
    println(data._4)

      //遍历
    val iterator: Iterator[Any] = data.productIterator
    while (iterator.hasNext){
      println(iterator.next())
    }

    //索引
    println(data.productElement(2))
  }
}
