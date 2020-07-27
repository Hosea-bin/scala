package com.atguigu.day09

object Scala105_Collection_Method3 {

  def main(args: Array[String]): Unit = {
    val list1: List[Int] = List(1,2,3,4)
    val list2: List[Int] = List(5,6,1,2,7)

    //合集
    println(list1.union(list2))
    //交集
    println(list1.intersect(list2))
    //差集  //当前集合，减去交叉重合的数据
    println(list1.diff(list2))
    println(list2.diff(list1))

    //拉链 (拉链后的数据取决于个数少的)
    val zipList: List[(Int, Int)] = list1.zip(list2)
    println(zipList)

    //自关联：数据和索引关联
    val zipList1: List[(Int, Int)] = list1.zip(list1)
    println(zipList1)

    val list: List[String] = List("a","b","c")
    val index: List[(String, Int)] = list.zipWithIndex
    val tuples: List[(Int, Int)] = list2.map(num=>(num,num))
    println(index)
    println(tuples)
    //将数据的一部分作为整体来使用的操作的函数
    val list111 = List(1,2,3,4,5,6,7)
    val iterator: Iterator[List[Int]] = list111.sliding(3,3)
    while (iterator.hasNext){
      println(iterator.next())
    }

  }
}
