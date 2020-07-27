package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Spark32_RDD_Operator16
{
  def main(args: Array[String]): Unit = {
    //		小功能：计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //k-v数据操作
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a",2),("b",1),("c",3)),1)
    //partitionBy:根据指定规则对数据进行分区
    //partitionBy参数为分区器对象
    //HashPartitioner & RangePartitioner

    //HashPartitioner分区规则是将当前数据的Key进行取余操作
    val rdd1: RDD[(String, Int)] = rdd.partitionBy(new HashPartitioner(2))
    rdd1.saveAsTextFile("output1" )

    //rdd.sortBy()  使用了RangePartitioner
    sc.stop()
  }
}
