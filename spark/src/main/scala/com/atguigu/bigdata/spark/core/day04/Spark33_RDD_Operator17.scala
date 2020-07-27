package com.atguigu.bigdata.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

object Spark33_RDD_Operator17
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //自定义分区
    val rdd: RDD[(String, String)] = sc.makeRDD(
      List(("a", "4"), ("a", "4"), ("a", "4"),
        ("b", "5"), ("c", "5"), ("b", "5")
      ),
      1
    )
    rdd
    rdd
    //partitionBy:根据指定规则对数据进行分区
    //partitionBy参数为分区器对象
    //HashPartitioner & RangePartitioner

    //HashPartitioner分区规则是将当前数据的Key进行取余操作
       val rdd1: RDD[(String, String)] = rdd.partitionBy(new MyPartitioner(3))
    //rdd1.collect().foreach(println)
    //mapPartitions :  强调的是每一个分区，所以传给算子的参数是分区类数据集
    //mapPartitionsWithIndex: 强调的是每一个分区号，所以提供给算子的参数是元组（分区号，分区内数据集合）
    val rdd2: RDD[(Int, (String, String))] = rdd1.mapPartitionsWithIndex(
      (index, datas) => {
        datas.map {
          data => (index, data)
        }
      }
    )
    rdd2
    rdd2.collect().foreach(println)
   // rdd2.saveAsTextFile("output")
    sc.stop()
  }
  //自定义分区器
  class MyPartitioner(num: Int)extends Partitioner{
    //获取分区数量
    override def numPartitions: Int = {
      num
    }
    //根据分区决定数据去哪个分区
    //方法的返回值，表示分区编号
    override def getPartition(key: Any): Int = {
      key match {
        case  "a" => 0
        case _ => 1
      }
    }
  }
}
