package com.atguigu.day03

object Scala20_Loop5 {
  def main(args: Array[String]): Unit = {
    //for 循环的表达式的返回值就是Unit
    //若需要for循环表达式的具体值，则使用yield关键字
    //此操作可将集合转换为另外一种集合
    val result=for(i <-1 to 5)yield{
      "* "*i
    }
    println("result "+result)

   // Thread.`yield`()
    }
}