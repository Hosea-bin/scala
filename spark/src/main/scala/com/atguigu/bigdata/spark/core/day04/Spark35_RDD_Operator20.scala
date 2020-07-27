package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark35_RDD_Operator20
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //groupByKey : 根据key 进行分组，
    //groupBy : 根据指定的规则对数据进行分组
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("a", 1), ("b", 2), ("a", 3)
      )
    )
    //第二个参数表示聚合后分区的数量
    val groupRDD: RDD[(String, Iterable[Int])] = rdd.groupByKey()
    val wordToCount: RDD[(String, Int)] = groupRDD.map {
      case (word, iter) => {
        (word, iter.sum)
      }
    }
    println(wordToCount.collect().mkString(","))

    sc.stop()
  }
}
