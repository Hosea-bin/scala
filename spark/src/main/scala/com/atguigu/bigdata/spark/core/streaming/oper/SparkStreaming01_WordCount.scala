package com.atguigu.bigdata.spark.core.streaming.oper

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming01_WordCount {
  def main(args: Array[String]): Unit = {

    //Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

    //2.初始化SparkStreamingContext
    val ssc = new StreamingContext(sparkConf,Seconds(3))

    //执行逻辑,从socket获取数据是一行一行获取的
    val socketDS: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop14",8888)

    val wordDS: DStream[String] = socketDS.flatMap(_.split(" "))
    val worToOneDS: DStream[(String, Int)] = wordDS.map((_,1))
    val wordToSumDS: DStream[(String, Int)] = worToOneDS.reduceByKey(_+_)

    wordToSumDS.print()
    //Drive程序执行streaming处理过程中不能结束
    //采集器在正常情况下启动不应停止
    //启动采集器
    ssc.start()
    //等待采集器结束
    ssc.awaitTermination()

  }
}
