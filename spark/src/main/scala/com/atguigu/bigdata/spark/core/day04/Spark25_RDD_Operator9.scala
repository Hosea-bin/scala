package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark25_RDD_Operator9{
  def main(args: Array[String]): Unit = {
    //		小功能：计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1,2,3,6,4,5,8,9,4,11,2))
    //true :抽取后放回
    //false : 抽取后不放回
    //第二个参数表示数据抽取的几率，抽取的几率，范围在[0,1]之间,0：全不取；1：全取；
    //第三个参数：随机数种子,可以确定数据的抽取
    // 随机数不随机，所谓随机数依靠随机算法实现
    val rdd: RDD[Int] = dataRDD.sample(
      false,
      0.5,
      1
    )
    val rdd1: RDD[Int] = dataRDD.sample(
      true,
      0.5
    )
    println(rdd.collect().mkString(","))
    println(rdd1.collect().mkString(","))
    sc.stop()
  }
}
