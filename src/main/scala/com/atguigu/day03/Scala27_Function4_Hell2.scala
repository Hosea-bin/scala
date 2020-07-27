package com.atguigu.day03

object Scala27_Function4_Hell2 {
  def main(args: Array[String]): Unit = {
  //函数可以作为对象赋值给变量
//   def sum(x:Int,y:Int):Int={
//     x + y
//   }

    def calcAnalysis(f:(Int,Int)=>Int)={
      val boy=23
      val girl=22
      f(boy,girl)
    }

    def calcAnalysis1(f:Int=>Int)={
      val boy=23
      f(boy)
    }

   // val f=sum _
   // println(calcAnalysis(f))
      //匿名函数
    val result=calcAnalysis((x:Int,y:Int) =>{x+y})
    val result1=calcAnalysis((x:Int,y:Int) =>x+y)
    val result3=calcAnalysis((x,y)=>x+y)

    val result2=calcAnalysis(_+_)
    println(result2)
  }
}