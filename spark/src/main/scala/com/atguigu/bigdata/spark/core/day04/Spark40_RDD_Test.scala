package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark40_RDD_Test
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1,2,7,8,8,9,4,11,2))

    val value: RDD[(Int, Iterable[Int])] = dataRDD.groupBy(word=>word)
    val value1: RDD[Int] = value.map(kv => {
      kv._1
    })
    value1
    println(value1.collect().mkString(","))
    sc.stop()
  }
}
