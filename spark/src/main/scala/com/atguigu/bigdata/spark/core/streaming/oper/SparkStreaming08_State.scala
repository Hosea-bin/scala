package com.atguigu.bigdata.spark.core.streaming.oper

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming08_State {

  def main(args: Array[String]): Unit = {
    //Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

    //2.初始化SparkStreamingContext
    val ssc = new StreamingContext(sparkConf,Seconds(3))
    ssc.sparkContext.setCheckpointDir("cp")


    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop14",8888)

    //将spark每个采集周期数据结果保存起来，然后和后续数据进行聚合

    // reduceByKey方法是无状态的，而我们需要有状态的数据操作

    // 有状态的目的是将每一个采集周期数据的计算结果临时保存起来
    ds.flatMap(_.split(" "))
      .map((_,1L))
      // 第一个参数：相同key的Value的集合
      // 第二个参数，相同key的缓冲区的数据，可能为空
      // 计算结果保存到检查点
      .updateStateByKey[Long](
        (seq:Seq[Long],buffer:Option[Long]) => {
          val newBufferValue: Long = buffer.getOrElse(0L)+seq.sum
          Option(newBufferValue)
        }
      ).print()
    ssc.start()
    ssc.awaitTermination()
  }
}
