package com.atguigu.day07

object Scala70_Interface {
  def main(args: Array[String]): Unit = {
    //class.getInterface().length
    //特质可以声明抽象方法
    val operate: MySQL = new MySQL
    operate.oper()
  }
}
//声明特质
trait Operate{
  //抽象方法-不完整方法
  def oper():Unit
}
class MySQL extends Operate{
   def oper(): Unit = {
    println("执行sql程序")
  }
}