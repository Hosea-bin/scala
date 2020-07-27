package com.atguigu.bigdata.spark.core.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark53_Dep1 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //3.new ParallelCollectionRDD
    val rdd: RDD[String] = sc.makeRDD(List("hello world", "hello spark"))
    println(rdd.dependencies)
    println("------------")

    //new MapPartitionRDD  -> new ParallelCollection
    val wordRDD: RDD[String] = rdd.flatMap(
      string => {
        string.split(" ")
      }
    )
    println(wordRDD.dependencies)
    println("-----------------")

    //new MapPartitionsRDD -> new MapPartitionsRDD
    val mapRDD: RDD[(String, Int)] = wordRDD.map(word=>(word,1))
    println(mapRDD.dependencies)
    println("------------------")

    //new ShuffledRDD -> new MapPartitionsRDD

    val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_+_)
    println(reduceRDD.dependencies)

    println(reduceRDD.collect().mkString(","))
    sc.stop()
  }
}
