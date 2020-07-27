package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark09_RDD_Operator2{
  def main(args: Array[String]): Unit = {
    //将旧的RDD转换为新的RDD
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
    // 3A 1A 1B       3B
    //          2A 2B   4A 4B
    //分区内数据按照顺序依次执行，分区间没有顺序
    val rdd1: RDD[Int] = rdd.map(x => {
      println("map A =" + x)
      x
    })
    rdd1
    val rdd2: RDD[Int] = rdd1.map(x => {
      println("map B =" + x)
      x
    })
    rdd2
    println(rdd2.collect().mkString(","))
    sc.stop()
  }
}
