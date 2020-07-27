package com.atguigu.day07

object Scala78_Collection_Array1 {
  def main(args: Array[String]): Unit = {

    val array = Array(1,2,3,4)

    //添加元素
    val newArray: Array[Int] = array :+ 5
    val newArray1: Array[Any] = array :+ "5"
    println(newArray.mkString(","))
    println(newArray1.mkString(","))

    val newArray2: Array[Int] = array.+:(5)
    println(newArray2.mkString(","))

    val newArray4: Array[Int] = 5+: array
    println(newArray4.mkString(","))

    val ints: Array[Int] = array ++ newArray
    println(ints.mkString(","))
  }
}
