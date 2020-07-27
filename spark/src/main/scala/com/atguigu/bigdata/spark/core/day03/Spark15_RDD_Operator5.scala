package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark15_RDD_Operator5{
  def main(args: Array[String]): Unit = {
    //将旧的RDD转换为新的RDD
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)


     val dataRDD: RDD[List[Int]] = sc.makeRDD(List(List(1,4),List(2,3)))

    val rdd: RDD[Int] = dataRDD.flatMap(list=>list)
    println(rdd.collect().mkString(","))
    sc.stop()
  }
}
