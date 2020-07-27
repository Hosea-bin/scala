package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark13_RDD_Operator4{
  def main(args: Array[String]): Unit = {
    //将旧的RDD转换为新的RDD
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //获取每个分区的最大分区号
    val dataRDD: RDD[Int] = sc.makeRDD(List(1,4,8,3,2,6,5,7),2)
    val rdd: RDD[(Int, Int)] = dataRDD.mapPartitionsWithIndex(
      (index, iter) => {
        List((index, iter.max)).iterator
      }
    )
    println(rdd.collect().mkString(","))

    sc.stop()
  }
}
