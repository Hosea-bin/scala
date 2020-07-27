package com.atguigu.day11

import scala.util.matching.Regex

object Scala129_Regex {
  def main(args: Array[String]): Unit = {
    //声明规则
    val r:Regex = "^s".r
    val r1: Regex = "s$".r

    //使用规则
    val s = "shello scala"
    val maybeString: Option[String] = r.findFirstIn(s)
    println(maybeString.get)

    //判断字符串是不是一个电话号码
    val r2: Regex = "^\\d{11}$".r
    val s1="02030033311"
    val maybeString1: Option[String] = r2.findFirstIn(s1)
    println(maybeString1.get)
  }
}
