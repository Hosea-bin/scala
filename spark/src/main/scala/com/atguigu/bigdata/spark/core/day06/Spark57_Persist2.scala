package com.atguigu.bigdata.spark.core.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark57_Persist2 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)
    sc.setCheckpointDir("cp")

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))

    val mapRDD: RDD[(Int, Int)] = rdd.map {
      num => {
        //println("map.....")
        (num, 1)
      }
    }
   //检查点操作会切断切断血缘关系，一旦数据丢失不会重新读取数据
    // 检查点将数据保存到分布式存储系统中，数据相对安全，不易丢失
    //切断血缘等同于产生新的数据源

    mapRDD.checkpoint()
    println(mapRDD.toDebugString)
    println(mapRDD.collect().mkString(","))
    println("**********")
    println(mapRDD.toDebugString)


    sc.stop()
  }
}
