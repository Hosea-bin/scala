package com.atguigu.bigdata.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_Memory_Par {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    val sc = new SparkContext(sparkConf)

    //从内存中创建RDD
    // RDD中的分区的数量就是并行度，设置并行度，其实就是分区数量
    //1、makeRDD的第一个参数，数据源
    //2.makeRDD 的第二个参数，默认并行度（分区数量）
    //    parallelize 并行
    //    numSlices: Int = defaultParallelism(默认并行度)

    // scheduler.conf.getInt("spark.default.parallelism", totalCores)
    //并行度默认会从Spark配置信息中获取spark.default.parallelism值
    //如果获取不到指定参数，会采用默认值
    //如果获取不到参数，会采用默认值totalCores(机器的总核数)
    //机器总核数=当前环境中可用的核数
    //local=>单核=>1
    //local[4]=>4核（4个线程）=>4

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),1)
    //println(rdd.collect().mkString(","))

    //将RDD的处理后数据保存到分区文件中
    rdd.saveAsTextFile("output")
    sc.stop()
  }
}
