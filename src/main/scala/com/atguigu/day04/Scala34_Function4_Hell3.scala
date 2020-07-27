package com.atguigu.day04

object Scala34_Function4_Hell3 {
  def main(args: Array[String]): Unit = {
  //1.将函数作为返回值使用
    def test(i:Int)={i*3}
    def fun()={test _}
    val a = fun()
    println(a(5))
    println(fun()(5))

    //函数作为返回值，会使用嵌套函数
    def fun1()={
      def test(i:Int)={
        i*3
      }
      test _
    }

    // 不想使用下划线返回对象则显示声明函数的返回值类型
    def fun2():Int=>Int={
      def test(i:Int)={
        i+4
      }
      test
    }

    println(fun2()(3))

    println(fun1()(20))
  }
}
