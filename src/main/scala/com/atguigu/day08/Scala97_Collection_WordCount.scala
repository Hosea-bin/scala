package com.atguigu.day08

import scala.io.Source

object Scala97_Collection_WordCount{

  def main(args: Array[String]): Unit = {

   //将文件中单词统计出现的次数并排序取前三名
    //从文件中获取数据，一行一行的字符串
    val dataList: List[String] = Source.fromFile("input/1.txt").getLines().toList
    println(dataList)
    //将一行一行的字符串拆分成一个个单词
    val wordList: List[String] = dataList.flatMap(data=>data.split(" "))
    //根据单词将数据分组
    val wordGroupMap: Map[String, List[String]] = wordList.groupBy(word=>word)
    //将分组后的单词进行字数统计
    val wordToCountMap: Map[String, Int] = wordGroupMap.map(kv =>(kv._1,kv._2.length))
    println(wordToCountMap)
    //将统计结果根据次数进行排序：降序
    println(wordToCountMap.toList)
    val sortList: List[(String, Int)] = wordToCountMap.toList.sortBy(kv =>kv._2)(Ordering.Int.reverse)
    //将排序后的结果取前三名
    val result: List[(String, Int)] = sortList.take(3)
    println(result)


  }
}
