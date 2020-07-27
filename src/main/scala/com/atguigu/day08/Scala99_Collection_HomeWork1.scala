package com.atguigu.day08

object Scala99_Collection_HomeWork1 {

  def main(args: Array[String]): Unit = {

   //将文件中单词统计出现的次数并排序取前三名
    //从文件中获取数据，一行一行的字符串
   val tuples: List[(String, Int)] = List(("hello",4),("hello spark",3),("hello spark scala",2),("hello spark scala hive",1))

     //获取每个元组中的字符串
   val list1: List[String] = tuples.map(kv =>kv._1)
   //拆分单词
   val wordlist: List[String] = list1.flatMap(data=>data.split(" "))

   //拆分单词得到元组
   val mapReduce: List[(String, Int)] = wordlist.map(x=>(x,1))
   //单词分组
   val stringToTuples: Map[String, List[(String, Int)]] = mapReduce.groupBy(_._1)
   //记录单词出现次数
   val stringToInt: Map[String, Int] = stringToTuples.mapValues(_.length)
   //排序
   val tuples1: List[(String, Int)] = stringToInt.toList.sortBy(kv=>kv._2)(Ordering.Int.reverse)
   //取前三名
   val result: List[(String, Int)] = tuples1.take(3)
   println(result)

  }
}
