package com.atguigu.day04

object Scala38_Function6 {
  def main(args: Array[String]): Unit = {
   //抽象控制
      //Scala支持将代码逻辑作为参数传递给函数
    //参数名： => 返回类型（Unit）
    def test(f: =>Unit):Unit={
        f
    }

    test(println("tax"))

    def fun:String={
      "lisi"
    }

    def test1(f: => String)={
      println(f)
    }

    test1(fun)
  }
}
