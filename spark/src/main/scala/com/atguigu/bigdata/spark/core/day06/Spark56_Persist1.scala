package com.atguigu.bigdata.spark.core.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark56_Persist1 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)
    sc.setCheckpointDir("cp")

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))

    val mapRDD: RDD[(Int, Int)] = rdd.map {
      num => {
        println("map.....")
        (num, 1)
      }
    }
   //将比较耗时，比较重要的数据一般会保存到分布式文件系统中
    // 使用checkpoint方法将数据保存到文件中
    // 执行checkpoint方法前应设定检查点的保存目录
    //检查点的操作为了数据的的准确性，执行时，会启动新的jab
    // 为了提高性能，检查点操作一般会和cache联合使用
    val cacheRDD: RDD[(Int, Int)] = mapRDD.cache()
    cacheRDD.checkpoint()
    println(cacheRDD.collect().mkString(","))
    println("**********")
    println(cacheRDD.collect().mkString("$"))

    sc.stop()
  }
}
