package com.atguigu.day11

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

case class Executor(id : Int,driverHost:String,driverPort:Int) {
  /**
   * 启动执行器
   */
  def startup()={
    println(s"执行器【$id】开始启动")

    println(s"执行器【$id】开始连接驱动器")
    val driverRef=new Socket(driverHost,driverPort)
    //接收Driver端发送的计算任务数据
    val driverRefIn = new ObjectInputStream(driverRef.getInputStream)
    val task: Task = driverRefIn.readObject().asInstanceOf[Task]
    driverRef.shutdownInput()
    //执行计算
    val i: Int = task.logic(id)
    //将计算结果返回Driver
    val driverRefOut = new ObjectOutputStream(driverRef.getOutputStream)
    driverRefOut.writeObject(Message(s"executorId=${id}&result=$i"))
    driverRefOut.flush()
    driverRef.close()
  }
}
