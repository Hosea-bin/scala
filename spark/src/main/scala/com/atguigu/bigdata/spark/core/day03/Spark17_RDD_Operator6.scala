package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark17_RDD_Operator6{
  def main(args: Array[String]): Unit = {
    //将每个分区的数据转换为数组
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //扁平化操作
     val dataRDD: RDD[Any] = sc.makeRDD(List(1,4,4,2,3),2)

    val rdd: RDD[Array[Any]] = dataRDD.glom()
    rdd.foreach(
      array=>{
        println(array.mkString(","))
      }
    )

    sc.stop()
  }
}
