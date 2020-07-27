package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark29_RDD_Operator14
{
  def main(args: Array[String]): Unit = {
    //		小功能：计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1,1,1,2,2,2,4,5,6,9,5))
    //sort by 默认升序
    //第三个参数可以改变分区
    val rdd: RDD[Int] = dataRDD.sortBy(num => num,true,3)
    println(rdd.collect().mkString(","))
    rdd.saveAsTextFile("output2")
    sc.stop()
  }
}
