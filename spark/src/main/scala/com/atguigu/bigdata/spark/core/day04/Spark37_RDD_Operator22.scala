package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.deploy.SparkSubmit
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark37_RDD_Operator22
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)
    SparkSubmit
    //reduceByKey ： 分区内和分区间计算规则相同
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("a", 1), ("a", 2), ("c", 3),
        ("c", 4), ("b", 6), ("c", 3)
      ),2
    )
    //aggregateByKey : 根据key 进行数据聚合
    //第一个参数列表中传递参数为zeroValue :计算的初始值
    //      用于分区内计算，当作初始值使用
    //第二个参数列表中传递参数为
     //            seqOp : 分区内计算规则,相同key的value的计算
    //             combOp: 分区间计算规则,相同key的value的计算
    //wordCount
    val result: RDD[(String, Int)] = rdd.aggregateByKey(0)(
      (x, y) => x+ y,
      (x, y) => x + y
    )
    result
    println(result.collect().mkString(","))
    //另外方法，foldByKey
    val result1: RDD[(String, Int)] = rdd.foldByKey(0)(_+_)
    println(result1.collect().mkString(","))
    sc.stop()
  }
}
