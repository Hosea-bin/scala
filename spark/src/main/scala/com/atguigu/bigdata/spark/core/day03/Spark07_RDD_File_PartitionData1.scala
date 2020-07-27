package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark07_RDD_File_PartitionData1{
  def main(args: Array[String]): Unit = {
    //math.min
    //math.max

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //hadoop 分区是以文件进行的，读取数据不能跨越文件
    //12/3 =4
    //(0,4) (4，6)
    val fileRDD: RDD[String] = sc.textFile("input1",3)
    fileRDD.saveAsTextFile("output")
    sc.stop()

  }
}
