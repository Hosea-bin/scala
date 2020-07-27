package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark16_RDD_Test{
  def main(args: Array[String]): Unit = {
    //将旧的RDD转换为新的RDD
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //扁平化操作
     val dataRDD: RDD[Any] = sc.makeRDD(List(List(1,4),4,List(2,3)))

    val rdd: RDD[Any] = dataRDD.flatMap(data => {
      data match {
        case list: List[_] => list
        case d => List(d)
      }
    })
    rdd
    println(rdd.collect().mkString(","))
    sc.stop()
  }
}