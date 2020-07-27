package com.atguigu.bigdata.spark.core.streaming.oper

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming09_Window {

  def main(args: Array[String]): Unit = {
    //Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

    //2.初始化SparkStreamingContext
    val ssc = new StreamingContext(sparkConf,Seconds(3))
    ssc.sparkContext.setCheckpointDir("cp")


    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop14",8888)

    //将spark每个采集周期数据结果保存起来，然后和后续数据进行聚合

    // reduceByKey方法是无状态的，而我们需要有状态的数据操作
    //将多个采集周期作为整体计算
    val wordDS: DStream[String] = ds.flatMap(_.split(" "))
    val wordToOneDS: DStream[(String, Int)] = wordDS.map((_,1))
    //窗口大小，滑动步长都为采集周期的整数倍
    val windowDS: DStream[(String, Int)] = wordToOneDS.window(Seconds(9))
    val result: DStream[(String, Int)] = windowDS.reduceByKey(_+_)
    result.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
