package com.atguigu.bigdata.spark.core.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark59_Acc {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("a",2),("a",3),("a",4)))

    var sum = 0

    rdd.foreach{
      case (word,count) =>{
        sum = sum + count
        println(sum)
      }
    }
    println("(a,"+sum+")")
    sc.stop()
  }
}
