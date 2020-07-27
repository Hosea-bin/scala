package com.atguigu.day03

import scala.util.control.Breaks

object Scala22_Loop7 {
  def main(args: Array[String]): Unit = {
    //scala没有循环关键字，以对象.方法的方式实现中断操作,使用抛出异常的方式中断循环
    Breaks.breakable{

      for (i <- 1 to 5){
        if (i ==4){
          Breaks.break()
        }
        println(i)
      }
    }
    println("ffawfda")
    }
}