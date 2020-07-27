package com.atguigu.day08

object Scala82_List1 {

  def main(args: Array[String]): Unit = {

    val list = List(1,2,3,4)

    //增加数据
    val list1: List[Int] = list :+ 1
    println(list1 eq list)

    list1.foreach(println)

    val list2: List[Int] = 1 +: list
    list2.foreach(println)

    val list3: List[Int] = list.updated(1,5)
    println(list eq list3)
    list3.foreach(println)
    }
}
