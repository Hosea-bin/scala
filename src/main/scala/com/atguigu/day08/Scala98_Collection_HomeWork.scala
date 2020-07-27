package com.atguigu.day08

import scala.io.Source

object Scala98_Collection_HomeWork {

  def main(args: Array[String]): Unit = {


    //从文件中获取数据，一行一行的字符串
   val tuples: List[(String, Int)] = List(("hello",4),("hello spark",3),("hello spark scala",2),("hello spark scala hive",1))
   //获取每个元组中的字符串
   val list1: List[String] = tuples.map(kv =>kv._1)

   //将一行行字符串拆分成单词
   val wordlist1: List[String] = list1.flatMap(data=>data.split(" "))

   //根据单词分组
   val wordTOGroup: Map[String, List[String]] = wordlist1.groupBy(word=>word)

   //分组后进行字数统计
   val wordToCount: Map[String, Int] = wordTOGroup.map(kv=>(kv._1,kv._2.length))

    //将统计结果根据次数进行排序：降序
   val sortList: List[(String, Int)] = wordToCount.toList.sortBy(kv=>kv._2)(Ordering.Int.reverse)
    //将排序后的结果取前三名
   val result: List[(String, Int)] = sortList.take(3)
   println(result)


  }
}
