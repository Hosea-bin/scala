package com.atguigu.bigdata.spark.core.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark52_Dep{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //3.new ParallelCollectionRDD
    val rdd: RDD[String] = sc.makeRDD(List("hello world", "hello spark"))
    println(rdd.toDebugString)
    println("------------")

    //new MapPartitionRDD  -> new ParallelCollection
    val wordRDD: RDD[String] = rdd.flatMap(
      string => {
        string.split(" ")
      }
    )
    println(wordRDD.toDebugString)
    println("-----------------")

    //new MapPartitionsRDD -> new MapPartitionsRDD
    val mapRDD: RDD[(String, Int)] = wordRDD.map(word=>(word,1))
    println(mapRDD.toDebugString)
    println("------------------")

    //new ShuffledRDD -> new MapPartitionsRDD
    //如果Spark的计算过程中某节点计算失败，则框架会重新尝试计算
    //RDD不保存，计算数据，但会保存元数据信息
    val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_+_)
    println(reduceRDD.toDebugString)

    println(reduceRDD.collect().mkString(","))
    sc.stop()
  }
}
