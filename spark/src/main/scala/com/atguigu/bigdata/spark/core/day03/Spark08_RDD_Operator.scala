package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark08_RDD_Operator{
  def main(args: Array[String]): Unit = {
    //将旧的RDD转换为新的RDD
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)
    
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))
    val rdd1: RDD[Int] = rdd.map(_*2)

    //读取数据
    println(rdd1.collect().mkString(","))
  }
}
