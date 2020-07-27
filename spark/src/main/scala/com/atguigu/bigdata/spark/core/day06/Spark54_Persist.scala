package com.atguigu.bigdata.spark.core.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark54_Persist {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))

    val mapRDD: RDD[(Int, Int)] = rdd.map {
      num => {
        println("map.....")
        (num, 1)
      }
    }
    //缓存cache底层其实调用的是persist方法
    //persist方法会采用不同的存储级别对数据进行持久化操作
    //cache缓存默认就是将数据保存到内存，如果内存不够，executor可以将内存的数据进行整理，然后可以丢弃数据
    //如果Executor端数据整理丢失，那么数据操作依然要重头执行
    //如果Cache后的数据重头执行数据操作的话，那么必须遵循血缘关系，Cache操作不能删除血缘关系
    //cache操作在行动算子执行后，会在血缘关系增加和缓存相关的依赖
    // cache 操作的结果不会切断血缘
    val cacheRDD: RDD[(Int, Int)] = mapRDD.cache()
    println(cacheRDD.toDebugString)
    println(cacheRDD.collect().mkString("&"))
    println(cacheRDD.toDebugString)
    println("**********")
    cacheRDD.saveAsTextFile("output")

    sc.stop()
  }
}
