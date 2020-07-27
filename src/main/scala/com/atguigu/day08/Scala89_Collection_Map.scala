package com.atguigu.day08

import scala.collection.mutable

object Scala89_Collection_Map{

  def main(args: Array[String]): Unit = {

    //ke不能重复  ,无序的
    val map: Map[String, Int] = Map("a"->1,"b"->2,"c"->3,"d"->4,"a"->4,"e"->5)
    println(map)
  }
}
