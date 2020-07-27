package com.atguigu.bigdata.spark.core.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark41_RDD_wordCount2
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val fileRDD: RDD[String] = sc.textFile("input/agent.log")
    //获取数据并切分
    val value: RDD[String] = fileRDD.map {
      line => {
        val datas: Array[String] = line.split(" ")
        datas(1) + "-" + datas(4)
      }
    }
    //(省份，广告)数量
    val value1: RDD[(String, Int)] = value.map(word=>(word,1)).reduceByKey(_+_)
    //value1.collect().foreach(println)

    //省份（广告，数量）
    val value2: RDD[(String, (String, Int))] = value1.map(
      line => {
        val datas: Array[String] = line._1.split("-")
        (datas(0), (datas(1), line._2))
      }
    )
    //将相同省份的数据分在一个组中
    val groupRDD: RDD[(String, Iterable[(String, Int)])] = value2.groupByKey()

    //分组后降序取前三
    val sortRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(
      iter => {
        iter.toList.sortWith(
          (left, right) => {
            left._2 > right._2
          }
        ).take(3)
      }
    )
    //打印到控制台
    sortRDD.collect().foreach(println)
    sc.stop()
  }
}
