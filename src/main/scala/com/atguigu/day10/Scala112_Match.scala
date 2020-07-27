package com.atguigu.day10

object Scala112_Match {
  def main(args: Array[String]): Unit = {
    //case _ 分支一般写在所有分支最后，模仿default语法
    //所有分支都不匹配，还没有case _ 分支，则会发生错误
    var a: Int =10
    var b: Int =20
    var operator: Char = '+'
    var result = operator match {
      case '+' => a+b
      case '-' => a-b
      case '*' => a*b
      case '/' => a/b
      case _ => "illegal"
    }
    println(result)
  }
}
