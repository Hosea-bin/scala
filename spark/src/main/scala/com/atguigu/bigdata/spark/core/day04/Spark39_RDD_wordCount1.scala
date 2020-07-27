package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark39_RDD_wordCount1
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("hello", 33), ("hive", 43), ("spark", 22), ("spark", 11), ("hive", 10), ("hello", 21)),2
    )

    //aggregateByKey 方法 1
    val result1: RDD[(String, Int)] = rdd.aggregateByKey(0)(_+_,_+_)
    println(result1.collect().mkString(","))

    //foldByKey 方法 2
    val result2: RDD[(String, Int)] = rdd.foldByKey(0)(_+_)
    println(result2.collect().mkString(","))

    //groupByKey 方法 3
    val result3: RDD[(String, Int)] = rdd.groupByKey().map {
      case (word, iter) => {
        (word, iter.sum)
      }
    }
    println(result3.collect().mkString(","))

    //reduceByKey 方法4
    val result4: RDD[(String, Int)] = rdd.reduceByKey(_+_)
    println(result4.collect().mkString(","))

    //combineByKey 方法5
    val result5: RDD[(String, (Int, Int))] = rdd.combineByKey(
      v => (v,1),
      (t: (Int, Int), v) => {
        (t._1 + v, 1)
      },
      (t1: (Int, Int), t2: (Int, Int)) => {
        (t1._1 + t2._1, 1)
      }
    )
    println(result5.map {
      case (key, (total, cnt)) => {
        (key, total / cnt)
      }
    }.collect().mkString(","))

    //groupBy  方法六
    val value: RDD[(String, Iterable[(String, Int)])] = rdd.groupBy(_._1)
    val value1: RDD[(String, Int)] = value.map {
      kv => {
        val count: Iterable[Int] = kv._2.map(kv => kv._2)
        (kv._1, count.sum)
      }
    }
    value1

    println(value1.collect().mkString(","))
//    println(value1.map {
//      case (key, list) => {
//        (key, list.sum)
//      }
//    }.collect().mkString(","))

    sc.stop()
  }
}
