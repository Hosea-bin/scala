package com.atguigu.day08

import scala.collection.mutable
import scala.collection.mutable.ArrayOps

object Scala100_Collection_HomeWork2{

  def main(args: Array[String]): Unit = {

   //将文件中单词统计出现的次数并排序取前三名

   val tuples: List[(String, Int)] = List(("hello",4),("hello spark",3),("hello spark scala",2),("hello spark scala hive",1))

    //数据拆分成字符串
   val flatMapList: List[(String, Int)] = tuples.flatMap(t => {
    val words: ArrayOps.ofRef[String] = t._1.split(" ")
    words.map(w => (w, t._2))
   })
    println(flatMapList)
  //将单词分组
    val groupByMap: Map[String, List[(String, Int)]] = flatMapList.groupBy(t=>t._1)
    println(groupByMap)

   //分组后进行结构转换，求单词出现次数的和
   val wordToSum: Map[String, Int] = groupByMap.map(t => {
    val count: List[Int] = t._2.map(tt => tt._2)
    (t._1, count.sum)
   })
    val wordToSumMap: Map[String, Int] = groupByMap.mapValues(v => {
      val ints: List[Int] = v.map(_._2)
      ints.sum
    })
    wordToSumMap

  println(wordToSum)
   //排序
   val sortList: List[(String, Int)] = wordToSum.toList.sortBy(kv =>kv._2)(Ordering.Int.reverse)

    //取前三名
   val result: List[(String, Int)] = sortList.take(3)
   println(result)
  }
}
