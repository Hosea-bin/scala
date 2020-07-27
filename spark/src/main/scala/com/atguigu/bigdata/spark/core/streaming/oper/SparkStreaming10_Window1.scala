package com.atguigu.bigdata.spark.core.streaming.oper

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming10_Window1 {

  def main(args: Array[String]): Unit = {
    //Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

    //2.初始化SparkStreamingContext
    val ssc = new StreamingContext(sparkConf,Seconds(3))
    ssc.sparkContext.setCheckpointDir("cp")

    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop14",8888)

    // 窗口
    val wordToOneDS: DStream[(String, Int)] = ds.map(num =>("key",num.toInt))

    //  适合重复数据比较大的场合
    val result: DStream[(String, Int)] = wordToOneDS.reduceByKeyAndWindow(
      (x, y) => {
        println(s"x = ${x},y = ${y}")
        x + y
      },
      (a, b) => {
        println(s"a = ${a},b = ${b}")
        a - b
      },
      Seconds(9)
    )
    result

    result.foreachRDD(rdd=>{
      rdd.foreach(
        data=>{
          println(data)
        }
      )
    }
    )


    ssc.start()
    ssc.awaitTermination()
  }
}
