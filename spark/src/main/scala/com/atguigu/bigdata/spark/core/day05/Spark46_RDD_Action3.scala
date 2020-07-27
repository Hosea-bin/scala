package com.atguigu.bigdata.spark.core.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark46_RDD_Action3
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //行动算子不会产生新的RDD
    //算子执行后，会获取作业执行结果
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)

    //aggregate:初始值，分区内会计算，分区间也会计算
    val i: Int = rdd.aggregate(5)(_+_,_+_)
    println(i)

    val i1: Int = rdd.fold(0)(_+_)
  }
}
