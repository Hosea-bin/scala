package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Memory_partitionData {
  def main(args: Array[String]): Unit = {
    //1.泛型
    //2.方法重写
    //3.伴生对象（类名访问方法）
    //4.模式匹配

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    val sc = new SparkContext(sparkConf)

    //内存中的集合数据按照平均分的方式进行区分处理
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
    rdd.saveAsTextFile("output")
    //内存中集合数据如果不能平均分，会将多余的数据放置在最后一个分区
    //分区如果不能够整除，会采取一个基本算法实现分配

    val rdd1: RDD[Int] = sc.makeRDD(List(1,2,3,4),3)
    rdd1.saveAsTextFile("output1")

    val rdd2: RDD[Int] = sc.makeRDD(List(1,2,3,4),4)
    rdd2.saveAsTextFile("output2")


    //源码解析
    //List(1,2,3,4,5)=Array(1,2,3,4,5)
    //(length=5,num=3)
    //(0,1,2)
    //=> 0 (0,1)
    // => 1 (1,3)
    // => 2 (3,5)
    // Array.slice =>切分数组 => (from .until)
    val rdd3: RDD[Int] = sc.makeRDD(List(1,2,3,4,5),3)

    sc.stop()
  }
}
