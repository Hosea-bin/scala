package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark20_RDD_Test{
  def main(args: Array[String]): Unit = {
    //	小功能：将List("Hello", "hive", "hbase", "Hadoop")根据单词首写字母进行分组
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[String] = sc.makeRDD(List("Hello", "hive", "hbase", "Hadoop"))
    val rdd= dataRDD.groupBy(
      data =>
       // data.substring(0,1)
      data.charAt(0)
      //data.charAt(0)

    )
    rdd

    rdd.collect().foreach {
      case (key, list) => {
        println("key:" + key + " list[" + list.mkString(",")+"]")
      }
    }

//    println(rdd.collect().mkString(","))
//    sc.stop()
  }
}
