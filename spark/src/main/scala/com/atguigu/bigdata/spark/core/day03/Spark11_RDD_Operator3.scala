package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark11_RDD_Operator3{
  def main(args: Array[String]): Unit = {
    //将旧的RDD转换为新的RDD
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
//    val rdd: RDD[Int] = dataRDD.mapPartitions(
//      iter => {
//        iter.map(_*2)
//      }
//    )
//    println(rdd.collect().mkString(","))

    val rdd: RDD[Int] = dataRDD.mapPartitions(
      iter => {
        iter.filter(_%2==0)
      }
    )
    println(rdd.collect().mkString(","))
  }
}
