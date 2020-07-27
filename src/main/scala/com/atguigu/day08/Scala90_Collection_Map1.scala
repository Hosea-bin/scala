package com.atguigu.day08

import scala.collection.mutable

object Scala90_Collection_Map1{

  def main(args: Array[String]): Unit = {

    //主要用于伴生对象构建对象,可变
    val map: mutable.Map[String, Int] = mutable.Map("a"->1,"b"->4,"c"->6)

    map.put("d",5)

    map("a")=3
    map.remove("b")

    println(map)
  }
}
