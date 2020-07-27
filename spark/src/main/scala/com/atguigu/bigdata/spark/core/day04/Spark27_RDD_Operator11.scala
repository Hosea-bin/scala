package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark27_RDD_Operator11{
  def main(args: Array[String]): Unit = {
    //		小功能：计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1,1,1,2,2,2),2)
    //
    val filterRDD: RDD[Int] = dataRDD.filter(num=>num%2==0)
    //当数据过滤后，发现数据不够均匀。可减少分区
    val coalesceRDD: RDD[Int] = filterRDD.coalesce(1)
    coalesceRDD.saveAsTextFile("output")
    sc.stop()
  }
}
