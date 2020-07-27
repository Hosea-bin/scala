package com.atguigu.day09

object Scala108_Collection_Method6 {

  def main(args: Array[String]): Unit = {
    //集合常用方法--折叠
    //scan中间处理结果保留
    val list = List(1,2,3,4)
    println("scan =>"+list.scan(0)(_+_))
    //集合扫描（左）
    println("scanLeft =>"+list.scanLeft(0)(_+_))
    //集合扫描（右）
    println("scanRight =>"+list.scanRight(0)(_+_))
  }
}
