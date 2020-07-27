package com.atguigu.bigdata.spark.core.day06

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark64_BC2{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc = new SparkContext(sparkConf)

    //广播变量 分布式共享只读变量

    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("b",2),("c",3)))
    val list =List(("a",1),("b",2),("c",3))

    //声明广播变量
    val bcList: Broadcast[List[(String, Int)]] = sc.broadcast(list)


    val rdd2: RDD[(String, (Int, Int))] = rdd1.map {
      case (word, count1) => {
        var count2 = 0
        //使用广播变量
        for (kv <- bcList.value) {
          if (kv._1 == word) {
            count2 = kv._2
          }
        }
        (word, (count1, count2))
      }
    }
    rdd2
    println(rdd2.collect().mkString(","))

    sc.stop()
  }
}
