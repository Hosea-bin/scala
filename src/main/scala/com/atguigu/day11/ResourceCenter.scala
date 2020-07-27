package com.atguigu.day11

import java.io.ObjectInputStream
import java.net.{ServerSocket, Socket}
import java.util.concurrent.Executor

object ResourceCenter {
  def main(args: Array[String]): Unit = {
      //1.创建服务，接受资源请求
      val receiver = new ServerSocket(9999)
      println("资源中心已启动")
      while (true){
        val driverRef: Socket = receiver.accept()
        new Thread(
          new Runnable {
            override def run(): Unit = {
              //接收Driver传递的数据
              val driverRefIn = new ObjectInputStream(driverRef.getInputStream)
              val message: Message = driverRefIn.readObject().asInstanceOf[Message]
              //正则表达式分解字符串
              val datas: Array[String] = message.content.split("=|&")
              val executorCount: Int = datas(1).toInt
              val driverHost: String = datas(3)
              val driverPort: Int = datas(5).toInt

              for(i <-1 to executorCount){
                val executor = new Executor(i,driverHost,driverPort)
                executor.startup()
              }
            }
          }
        ).start()
      }
  }
}
