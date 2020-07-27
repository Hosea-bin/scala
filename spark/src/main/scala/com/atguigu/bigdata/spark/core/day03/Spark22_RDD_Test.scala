package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object
Spark22_RDD_Test{
  def main(args: Array[String]): Unit = {
    //		小功能：WordCount。
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val fileRDD: RDD[String] = sc.textFile("input/word.txt")

    //单词切分
    val wordRDD: RDD[String] = fileRDD.flatMap(_.split(" "))

    //结构转换
   // val mapRDD: RDD[(String, Int)] = wordRDD.map(word =>(word,1))

    //分组聚合
    val groupRDD: RDD[(String, Iterable[String])] = wordRDD.groupBy(word=>word)

    //分组聚合
//    groupRDD.map{
//      case (word , iter)=>{
//        (word,iter.size)
//      }
//    }
    //println(groupRDD.collect().mkString(","))
    val rdd: RDD[(String, Int)] = groupRDD.map {
      kv => {
        (kv._1, kv._2.size)
      }
    }
    println(rdd.collect().mkString(","))

    sc.stop()
  }
}
