package com.atguigu.bigdata.spark.core.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Spark62_BC{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc = new SparkContext(sparkConf)

    //广播变量
    //join 会有笛卡尔乘积的效果，数据量会极具增多。如果有shuffle过程，那么性感会非常低
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("b",2),("c",3)))
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("b",2),("c",3)))

    val joinRDD: RDD[(String, (Int, Int))] = rdd1.join(rdd2)
    println(joinRDD.collect().mkString(","))
    sc.stop()
  }
}
