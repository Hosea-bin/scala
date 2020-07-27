package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark31_RDD_Operator15
{
  def main(args: Array[String]): Unit = {
    //		小功能：计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val rdd1: RDD[Int] = sc.makeRDD(List(1,3,4,2),2)
    //
   val rdd2: RDD[Int] = sc.makeRDD(List(3,4,5,6),2)
    val rdd0: RDD[String] = sc.makeRDD(List("a","v","c","l"),2)

    //并集
//    val rdd3: RDD[Int] = rdd1.union(rdd2)
//    println(rdd3.collect().mkString(","))
//    rdd3.saveAsTextFile("output")
//    //交集,数据会被打乱重组
//    val rdd5: RDD[Int] = rdd1.intersection(rdd2)
//    println(rdd5.collect().mkString(","))
//    rdd5.saveAsTextFile("output1")
//    //差集：数据被打乱重组
//    val rdd6: RDD[Int] = rdd1.subtract(rdd2)
//    println(rdd6.collect().mkString(","))
//    rdd6.saveAsTextFile("output2")
//    //拉链
//
    val rdd7: RDD[(Int, Int)] = rdd1.zip(rdd2)
    println(rdd7.collect().mkString(","))
    rdd7.saveAsTextFile("output4")
    sc.stop()
  }
}
