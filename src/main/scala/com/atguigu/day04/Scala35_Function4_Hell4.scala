package com.atguigu.day04

object Scala35_Function4_Hell4 {
  def main(args: Array[String]): Unit = {
    //匿名函数肯定为闭包，将函数赋值给变量也是闭包，嵌套的内部函数在外部使用也会有闭包
    //函数在使用外部变量时，如果外部变量失效，会将这个变量包含在当前函数的内部
    //形成闭合的效果，改变变量的生命周期

    def test(i:Int)={
     def sum(j:Int)={
       i+j
     }
     sum _
   }


    println(test(10)(20))
  }
}
