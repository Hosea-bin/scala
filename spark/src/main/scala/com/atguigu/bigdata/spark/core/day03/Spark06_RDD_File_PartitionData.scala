package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark06_RDD_File_PartitionData {
  def main(args: Array[String]): Unit = {
    //math.min
    //math.max

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //1.Spark读取文件采用的是Hadoop的读取规则
    //文件切片规则：以字节方式来切片
    //数据读取规则：以行为单位读取
    //totalSize =10
    //goalSize =totalSize /numSplits =10/2 =5
    //所谓最小分区，取决于总的字节数能否整除分区数，并且剩余字节达到一个比例

  }
}
