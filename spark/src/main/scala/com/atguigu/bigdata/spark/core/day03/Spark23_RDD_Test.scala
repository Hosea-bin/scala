package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark23_RDD_Test{
  def main(args: Array[String]): Unit = {
    //		小功能：从服务器日志数据apache.log中获取2015年5月17日的请求路径
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val fileRDD: RDD[String] = sc.textFile("input/apache.log")
    val dataRDD: RDD[String] = fileRDD.map(
      line => {
        val datas: Array[String] = line.split(" ")
        datas(3)+datas(6)
      }
    )

    val rdd: RDD[String] = dataRDD.filter(
      data => {
        data.substring(0, 5) == "17/05"
      }
    )

    rdd.collect().foreach(println)


    sc.stop()
  }
}
