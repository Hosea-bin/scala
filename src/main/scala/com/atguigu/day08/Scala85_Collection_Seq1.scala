package com.atguigu.day08

object Scala85_Collection_Seq1{

  def main(args: Array[String]): Unit = {

   val nil: List[Nothing]= Nil
   val list: List[Int] = 1::2::3::Nil
   println(list)

    val list1: Any = 4::5::list::Nil
    println(list1)

    val list2: Any = 4::5::list:::Nil
    println(list2)
  }
}
