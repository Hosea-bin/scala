package com.atguigu.bigdata.spark.core.streaming.oper

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext, StreamingContextState}

object SparkStreaming12_Stop {

  def main(args: Array[String]): Unit = {
    //Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

    //2.初始化SparkStreamingContext
    val ssc = new StreamingContext(sparkConf,Seconds(3))
   // ssc.sparkContext.setCheckpointDir("cp")

    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop14",8888)

    // 窗口
    val wordOneDS: DStream[(String, String)] = ds.map(num =>("key",num))

    wordOneDS.print()
    ssc.start()

    //当业务升级的场合，或逻辑发生变化，Stop方法一般不会放置在main线程完成
    //Stop方法一般不会放置在main线程中完成，需要将Stop方法使用新的线程完成调用
    new Thread(new Runnable {
      override def run(): Unit = {
        //Stop方法的调用不应该是线程启动后马上调用
        // Stop方法调用的时机不容易确定，需要周期性判断
        while (true){
          Thread.sleep(1000)
          //关闭的时机一般不会使用业务操作，一般采用第三方的程序或存储进行判断
          // HDFS => /stopSpark
          // zk
          // mysql
          // redis
          // 2.优雅的关闭
          val state: StreamingContextState = ssc.getState()
          if(state == StreamingContextState.ACTIVE){
            ssc.stop(true,true)
            //SparkStreaming如果停止后，当前的线程也应该停止
            System.exit(0)
          }
        }
      }
    }).start()
    ssc.awaitTermination()
  }
}
