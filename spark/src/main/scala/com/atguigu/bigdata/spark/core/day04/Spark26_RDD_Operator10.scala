package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark26_RDD_Operator10{
  def main(args: Array[String]): Unit = {
    //		小功能：计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1,2,7,8,8,9,4,11,2))

    val rdd: RDD[Int] = dataRDD.distinct()

    println(rdd.collect().mkString(","))
    sc.stop()
  }
}
