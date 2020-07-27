package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark21_RDD_Test{
  def main(args: Array[String]): Unit = {
    //		小功能：从服务器日志数据apache.log中获取每个时间段访问量。
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val fileRDD: RDD[String] = sc.textFile("input/apache.log")
    val timeRDD: RDD[String] = fileRDD.map(
      line => {
        val datas: Array[String] = line.split(" ")
        datas(3)
      }
    )
    val timeRDD1: RDD[String] = timeRDD.map {
      line => line.substring(0, 13)
    }
    timeRDD1
    val rdd: RDD[(String, Iterable[String])] = timeRDD1.groupBy {
      data => data
    }
    //timeRDD1.collect().foreach(println)

    rdd.collect().foreach {
      case (key, list) => {
        println("时间段：" + key + " 的访问量：" + list.size)
      }
    }

    sc.stop()
  }
}
