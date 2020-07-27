package com.atguigu.day08


object Scala101_Collection_HomeWork3{

  def main(args: Array[String]): Unit = {

   //将文件中单词统计出现的次数并排序取前三名

   val tuples: List[(String, Int)] = List(("hello",4),("hello spark",3),("hello spark scala",2),("hello spark scala hive",1))
   //从文件中获取数据，一行一行的字符串
    val tuples1: List[(String, Int)] = tuples.map(t => {
//      val str: String = t._1 + " "
//      (str, t._2)
     (t._1+" ",t._2)
    })
    //各元组数据相乘，记录总单词数
    val dataLists: List[String] = tuples1.map(t=>t._1*t._2)
   //val strings: List[String] = tuples1.map(t=>(t._1+" ")*t._2)
    //拆分单词
    val wordList: List[String] = dataLists.flatMap(data=>data.split(" "))

    //单词分组
    val wordGroupMap: Map[String, List[String]] = wordList.groupBy(word=>word)

   //次数统计
    val wordCount: Map[String, Int] = wordGroupMap.map(kv =>(kv._1,kv._2.length))

    //排序
    val sortList: List[(String, Int)] = wordCount.toList.sortBy(kv =>kv._2)(Ordering.Int.reverse)
   //取前三名
    val result: List[(String, Int)] = sortList.take(3)
    println(result)
  }
}
