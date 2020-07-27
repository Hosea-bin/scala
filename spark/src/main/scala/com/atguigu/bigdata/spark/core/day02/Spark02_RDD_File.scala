package com.atguigu.bigdata.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_File {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)

    //Spark 从磁盘中创建RDD
    //path:读取文件路径，可以设置相对路径，IDEA中，相对路径从项目的根开始查找
    //path路径根据环境的不同，自动发生改变


    //Spark读取文件默认是一行一行读取文件内容

    //如果路径指定的是文件目录，则目录的文本文件会被读取
    //val fileRDD: RDD[String] = sc.textFile("input1")
    //读取指定文件
    //val fileRDD: RDD[String] = sc.textFile("input/word.txt")
    //文件路径采用通配符
    val fileRDD: RDD[String] = sc.textFile("input1/word*.txt")
    //文件路径可以指向第三方存储系统
    println(fileRDD.collect().mkString(","))
  }
}
