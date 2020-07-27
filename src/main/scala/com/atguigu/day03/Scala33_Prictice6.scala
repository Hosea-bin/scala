package com.atguigu.day03

import scala.io.StdIn
  //写一个递归函数，获取第n个斐波那契数，0，1，1，2，3，5，8.
//打印99乘法表
object Scala33_Prictice6 {
  def main(args: Array[String]): Unit = {
    def fib (n : Int):Int={
      if(n==1){
        0
      }else if(n==2){
        1
      }else{
        fib(n-1) + fib(n-2)
      }
    }
    println(fib(6))

  }

}