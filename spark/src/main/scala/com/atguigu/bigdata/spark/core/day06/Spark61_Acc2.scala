package com.atguigu.bigdata.spark.core.day06

import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Spark61_Acc2{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc = new SparkContext(sparkConf)

    //累加器 作用：累加数据
    //将累加器变量注册到Saprk中
    //执行计算时，spark会将累加器发送到executor端执行
    //执行完毕后，executor会将执行结果返回Driver端
    //driver端获取多个累加器的结果，然后两两合并
     val rdd= sc.makeRDD(List(1,2,3,4))
    //声明累加器
    val sum: LongAccumulator = sc.longAccumulator("sum")

    rdd.foreach(
      num =>{
        //使用累加器
        sum.add(num)}
    )
    //获取累加器结果
    println("结果为 ="+sum.value)
    sc.stop()
  }
}
