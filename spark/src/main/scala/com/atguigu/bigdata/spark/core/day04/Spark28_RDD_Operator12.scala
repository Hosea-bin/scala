package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark28_RDD_Operator12
{
  def main(args: Array[String]): Unit = {
    //		小功能：计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1,1,1,2,2,2,4,5,6,9,5),2)
    //
    //val filterRDD: RDD[Int] = dataRDD.filter(num=>num%2==0)
    //分区缩减时，数据不会打乱重写组合，没有shuffle过程

    //如果必须扩大分区，则必须打乱数据后重新组合，使用Shuffle

    val coalesceRDD: RDD[Int] = dataRDD.coalesce(4,true)
    coalesceRDD.saveAsTextFile("output")
    sc.stop()
  }
}
