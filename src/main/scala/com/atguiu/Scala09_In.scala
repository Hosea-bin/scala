package com.atguiu

import scala.io.StdIn

object Scala09_In {
  def main(args: Array[String]): Unit = {
    val age = StdIn.readInt()
    println("年龄是 "+age)
  }

}
