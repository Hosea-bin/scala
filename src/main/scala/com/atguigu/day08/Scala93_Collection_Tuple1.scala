package com.atguigu.day08

import scala.collection.mutable

object Scala93_Collection_Tuple1{

  def main(args: Array[String]): Unit = {

   //元组  对偶元组
   // val data = ("a",1)
    val map: mutable.Map[String, Int] = mutable.Map(("a,b",1),("b,c",2),("c",3))
    println(map)

    for(kv <- map){
      println(kv._1,kv._2)
    }

    for(kv <- map){
      println(kv._1)
    }

  }
}
