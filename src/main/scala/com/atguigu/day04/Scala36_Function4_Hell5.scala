package com.atguigu.day04

object Scala36_Function4_Hell5 {
  def main(args: Array[String]): Unit = {
    //函数使用外部的变量，当前场合不叫闭包
    val a =10

    def test()={
      val b=a+10
      println(b)
    }
    test() //  a未改变生命周期，一直有效，不能产生闭包

    val c=test _   // 函数赋值给变量使用，产生闭包

    //匿名
    def teat1(f:(Int)=>Int)={
      println(f(10))
    }
    teat1((i:Int)=>{i*3})


    def test3()={
      def sum()={
        println("inner sun")
      }
      sum()
    }

    def test4():()=>Unit={
      def sum():Unit={
        println("inner sun")
      }
     sum
    }
    test4()()
    test3()

  }
}
