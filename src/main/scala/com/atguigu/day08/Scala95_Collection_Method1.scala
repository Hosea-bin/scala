package com.atguigu.day08

object Scala95_Collection_Method1{

  def main(args: Array[String]): Unit = {

   //常用方法
     val list = List(1,2,3,4)

    println(list.head)
    println(list.tail)

    println(list.last)
    println(list.init)

    println(list.reverse)
    println(list.reverse.head)

    println(list.contains(5))

    println(list.toSet)
    println(list.distinct)

    println(list.take(3))
    println(list.takeRight(3))
    println(list.drop(2))
    println(list.dropRight(2))
  }
}
