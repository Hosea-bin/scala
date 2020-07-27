package com.atguigu.bigdata.spark.core.streaming.oper

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming13_Continue{

  def main(args: Array[String]): Unit = {

    // 窗口
    val sc = StreamingContext.getActiveOrCreate("cp", getStreamingContext)

    sc.start()
    sc.awaitTermination()
  }
    def getStreamingContext():StreamingContext={
      //Spark环境
      val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

      //2.初始化SparkStreamingContext
      val ssc = new StreamingContext(sparkConf,Seconds(3))
      ssc.checkpoint("cp")

      val ds: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop14",8888)
      ds.print()
      ssc

    }


}
