package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark18_RDD_Operator7{
  def main(args: Array[String]): Unit = {
    //将每个分区的数据转换为数组
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1, 4, 4, 2, 3, 5), 3)

    //分组  group by :返回值为元组
    //会导致数据不均匀


//    val rdd: RDD[(Int, Iterable[Int])] = dataRDD.groupBy(
//      num => {
//        num % 2
//      }
//    )
    val rdd: RDD[(Int, Iterable[Int])] = dataRDD.groupBy(
    x => x % 2,2
    )
    rdd
    rdd.collect().foreach {
    case (key,list) =>{
      println("key:"+key+"list["+list.mkString(",")+"]")
    }
  }
    //rdd.saveAsTextFile("output")

    println("分组后的数据分区数量=" + rdd.glom().collect().length)
    sc.stop()


  }
}
