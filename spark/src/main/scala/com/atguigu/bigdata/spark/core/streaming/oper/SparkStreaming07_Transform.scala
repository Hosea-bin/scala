package com.atguigu.bigdata.spark.core.streaming.oper

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming07_Transform {
  def main(args: Array[String]): Unit = {

    //Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

    //2.初始化SparkStreamingContext
    val ssc = new StreamingContext(sparkConf,Seconds(3))

    //3.转换
    //code Driver
    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop14",8888)
    val newDS: DStream[String] = ds.transform((
      rdd => {  // 执行N
        rdd.map(_ * 2)
      }
      ))
    val newDS1: DStream[String] = ds.map(_*2)

    newDS.print()

    ssc.start()

    ssc.awaitTermination()

  }

}
