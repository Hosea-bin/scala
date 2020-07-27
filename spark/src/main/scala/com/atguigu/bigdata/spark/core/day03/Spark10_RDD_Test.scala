package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark10_RDD_Test{
  def main(args: Array[String]): Unit = {
    //将旧的RDD转换为新的RDD
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //从服务器日志数据apache.log中获取用户请求的URL资源路径
    val fileRDD: RDD[String] = sc.textFile("input/apache.log")

    val urlRDD: RDD[String] = fileRDD.map(
      line => {
        val datas: Array[String] = line.split(" ")
        datas(6)
      }
    )
    urlRDD
    //urlRDD.collect().foreach(println)
    urlRDD.collect().foreach(println(_))
    sc.stop()
  }
}
