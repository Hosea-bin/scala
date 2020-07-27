package com.atguigu.bigdata.spark.core.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Spark60_Acc1 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc = new SparkContext(sparkConf)

    //使用累加器完成数据的累加
     val rdd= sc.makeRDD(List(1,2,3,4))
    //声明累加器
    val sum: LongAccumulator = sc.longAccumulator("sum")

    rdd.foreach(
      num =>{sum.add(num)}
    )
    println("结果为 ="+sum.value)
    sc.stop()
  }
}
