package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object Spark34_RDD_Operator19
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //reduceByKey : 根据key 进行分组，然后对value进行聚合
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("a", 1), ("b", 2), ("a", 3)
      )
    )
    //第二个参数表示聚合后分区的数量
    val rdd1: RDD[(String, Int)] = rdd.reduceByKey(_+_,2)
    println(rdd1.collect().mkString(","))

    sc.stop()
  }
}
