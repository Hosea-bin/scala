package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark14_RDD_Test{
  def main(args: Array[String]): Unit = {
    //将旧的RDD转换为新的RDD
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //获取第二个分区的数据
    val dataRDD: RDD[Int] = sc.makeRDD(List(1,4,8,3,2,6,5,7),3)


    val rdd: RDD[Int] = dataRDD.mapPartitionsWithIndex(
      (index, iter) => {
        if (index == 0) {
          iter
        } else {
          Nil.iterator
        }
      }
    )
    println(rdd.collect().mkString(","))

    sc.stop()
  }
}
