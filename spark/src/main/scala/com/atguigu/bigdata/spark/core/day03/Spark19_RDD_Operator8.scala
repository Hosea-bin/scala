package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark19_RDD_Operator8{
  def main(args: Array[String]): Unit = {
    //将每个分区的数据转换为数组
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1, 4, 4, 2, 3, 5), 3)

    //过滤
    //满足条件的保留，不满足的丢弃
    val rdd: RDD[Int] = dataRDD.filter(
      num => {
        num % 2 == 0
      }
    )
    //rdd.collect().foreach(println)
    println(rdd.collect().mkString(","))
    sc.stop()
  }
}
