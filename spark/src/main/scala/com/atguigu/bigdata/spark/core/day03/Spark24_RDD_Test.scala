package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark24_RDD_Test{
  def main(args: Array[String]): Unit = {
    //		小功能：计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1,2,3,6,4,5,8,9,4,11,2),3)
    val dataRDD1: RDD[Array[Int]] = dataRDD.glom()

    //var sum: Int = 0
//    val rdd: Unit = dataRDD1.foreach(
//      array => {
//        println(array.max)
//        array.max
//      }
//    )

    val value: RDD[Int] = dataRDD1.map {
      data => {
        data.max
      }
    }

    //println(value1.collect().mkString(","))
     val d: Int = value.sum().toInt

    println(value.collect().mkString(","))
    println(d)
    sc.stop()
  }
}
