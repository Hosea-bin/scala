package com.atguigu.bigdata.spark.core.streaming.oper

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object SparkStreaming03_Queue {
  def main(args: Array[String]): Unit = {

    //Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

    //2.初始化SparkStreamingContext
    val ssc = new StreamingContext(sparkConf,Seconds(3))

    //执行逻辑,从socket获取数据是一行一行获取的
    //创建RDD队列
     val queue = new mutable.Queue[RDD[String]]()
    val queueDS: InputDStream[String] = ssc.queueStream(queue)

    queueDS.print()
    ssc.start()
    //循环创建并向RDD队列中放入RDD
    for(i <- 1 to 5){
      val rdd: RDD[String] = ssc.sparkContext.makeRDD(List(i.toString))
      queue.enqueue(rdd)
      Thread.sleep(1000)
    }
    ssc.awaitTermination()

  }
}
