package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark38_RDD_Operator23
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //reduceByKey ： 分区内和分区间计算规则相同
    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("a", 33), ("a", 44), ("b", 22), ("a", 11), ("b", 10), ("b", 21)),2
    )
    //combinByKey : 每个key的平均值，key总和除以key数量
    //
    //第一个参数：将计算的第一个值进行结构转换
    //第二个参数： 分区内的计算规则
    //第三个参数： 分区间的计算规则

    val result: RDD[(String, (Int, Int))] = rdd.combineByKey(
      v => (v, 1),
      (t: (Int, Int), v) => {
        (t._1 + v, t._2 + 1)
      },
      (t1: (Int, Int), t2: (Int, Int)) => {
        (t1._1 + t2._1, t1._2 + t2._2)
      }
    )
    result.map{
      case (key,(total,cnt)) =>{
        (key,total/cnt)
      }
    }.collect().foreach(println)
    //result.collect().foreach(println)

    sc.stop()
  }
}
