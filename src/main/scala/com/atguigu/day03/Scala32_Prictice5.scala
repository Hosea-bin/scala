package com.atguigu.day03

import scala.io.StdIn

//打印99乘法表
object Scala32_Prictice5 {
  def main(args: Array[String]): Unit = {
    println("请输入1-9的整数")
    val x = StdIn.readInt()
    myPrint(x)
  }

  def myPrint(n:Int)={
    for(i <- 1 to n){
      for(j<-1 to i){
        print(i +"*"+j+"="+ i*j+"\t")
      }
      println()
    }
  }

}