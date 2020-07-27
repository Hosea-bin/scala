package com.atguigu.bigdata.spark.core.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark43_RDD_Action
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //行动算子不会产生新的RDD
    //算子执行后，会获取作业执行结果
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))
    rdd.collect().foreach(println)
    sc.stop()
  }
}
