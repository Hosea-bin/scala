package com.atguigu.day04

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket


object Scalar42_work_Client extends Serializable {
  def main(args: Array[String]): Unit = {
    val socket=new Socket("localhost",9999)

    def fun1(x:Int) = x+2
    val a=fun1 _
    val ob=new ObjectOutputStream(socket.getOutputStream())
    ob.writeObject(a)
    println("发送代码：")
    socket.close()

  }
}
