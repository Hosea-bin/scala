package com.atguigu.day04

object Scala37_Function5 {
  def main(args: Array[String]): Unit = {
   def test()={
    def fun()={
      println("ddddd")
    }
    fun _
  }

    test()()

    //函数柯里化
    def test1()()={
      println("ddddddddd")
    }
    test1()()

    def test2(i:Int)(j:Int)(f:(Int,Int)=>Int)={
      f(i,j)
    }

    println(test2(1)(3)(_ + _))

  }
}
