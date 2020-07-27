package com.atguigu.bigdata.spark.core.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark49_RDD_Action6 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //行动算子不会产生新的RDD
    //算子执行后，会获取作业执行结果
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
    
    //集合的方法的代码就是当前节点（Driver）中执行的
    //foreach 方法在当前节点的内存完成数据的循环
    rdd.collect().foreach(println)

    //rdd 的方法称之为算子
    println("********")
    //算子的逻辑代码在分布式计算节点Executor中执行
    //foreach算子可以循环在不同的计算节点完成
    //算子之外的代码在Driver端执行

  }
}
