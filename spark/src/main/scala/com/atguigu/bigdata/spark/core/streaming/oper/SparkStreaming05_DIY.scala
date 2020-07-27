package com.atguigu.bigdata.spark.core.streaming.oper

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming05_DIY {
  def main(args: Array[String]): Unit = {

    //Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

    //2.初始化SparkStreamingContext
    val ssc = new StreamingContext(sparkConf,Seconds(3))

    //执行逻辑,从socket获取数据是一行一行获取的
    val ds: ReceiverInputDStream[String] =
      ssc.receiverStream(new MyReceiver("hadoop14",8888))
    ds.print()
    ssc.start()

    ssc.awaitTermination()

  }
  //自定义数据采集器
  // 1. 继承Receiver,定义泛型
  //    Receiver的构造方法有参数的，所以子类在继承时，应传递这个参数
  // 2. 重写方法
  //    onStart , onStop
  class MyReceiver(host:String,port:Int) extends Receiver[String](StorageLevel.MEMORY_ONLY){
    private var socket:Socket= _

    def receive()={
      val reader = new BufferedReader(   //缓冲流
        new InputStreamReader(   //转换流
          socket.getInputStream,
          "UTF-8"
        )
      )
      var s :String = null
      while (true){
        s = reader.readLine()
        if(s != null){
          //将获取的数据保存到框架内部进行封装
          store(s)
        }
      }

    }
    override def onStart(): Unit = {
      socket= new Socket(host,port)
      new Thread("Socket Receiver"){
        setDaemon(true)    //守护线程
        override def run() {
          receive()
        }
      }.start()
    }

    override def onStop(): Unit = {
      socket.close()
      socket = null
    }
  }
}
