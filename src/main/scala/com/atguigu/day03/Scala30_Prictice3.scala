package com.atguigu.day03

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Scala30_Prictice3 {
  def main(args: Array[String]): Unit = {
    def test(a:Int,b:Int) ={
      a*b
    }

    def c(c:Int)={
      val a=10
      val b=20
      c*test(a,b)
    }

    println(c(5))
  }
}