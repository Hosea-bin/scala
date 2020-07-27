package com.atguigu.day05

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

import com.atguigusummer.framework.bean.Task
import com.atguigusummer.framework.core.TApplication

object Scala45_Function_Client extends TApplication{
    def main(args: Array[String]): Unit = {

        start("socket"){
            val client:Socket = envData.asInstanceOf[Socket]
            val outObject = new ObjectOutputStream(client.getOutputStream)
            //Out -> function ->对象
            val task = new Task()
            task.data=10
            task.logic=(x:Int)=>{x*2}
            //val fun = (x:Int)=>{x * 2} //函数对象
            outObject.writeObject(task)
            outObject.flush()
            //关闭socket的输出流，但是输入流可用
            client.shutdownOutput()
            //In
            val inObject = new ObjectInputStream(client.getInputStream)
            val result :Int= inObject.readObject().asInstanceOf[Int]
            println("获取计算结果："+ result)
            //client.shutdownInput()
            inObject.close()

        }
    }
}
