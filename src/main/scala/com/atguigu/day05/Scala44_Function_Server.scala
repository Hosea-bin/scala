package com.atguigu.day05

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

import com.atguigu.day05.Scala45_Function_Client.envData
import com.atguigusummer.framework.bean.Task
import com.atguigusummer.framework.core.TApplication

object Scala44_Function_Server extends TApplication{
  def main(args: Array[String]): Unit = {

    start("serverSocket"){
      val server :ServerSocket= envData.asInstanceOf[ServerSocket]
      while (true){
        var client: Socket = server.accept()
        new Thread(
          new Runnable {
          override def run(): Unit = {
            //In
            val inObject = new ObjectInputStream(client.getInputStream)
            val task = inObject.readObject().asInstanceOf[Task]
            //inObject.close()
            //关闭socket的输入流，但同时输出流可用
            client.shutdownInput()
            //Out
            val outObject = new ObjectOutputStream(client.getOutputStream)
            val result = task.compute()
            //Out -> function ->对象
            outObject.writeObject(result)
            outObject.flush()
            outObject.close()

            if(!client.isClosed){
              client.close()
            }
            client = null
          }
        }).start()
      }
    }
  }
}
