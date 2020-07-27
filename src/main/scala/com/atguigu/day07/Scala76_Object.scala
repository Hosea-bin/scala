package com.atguigu.day07

object Scala76_Object {
  def main(args: Array[String]): Unit = {
  println(Color.RED.id)
    println(Color.BLUE)
  }
}
object Color extends Enumeration {
  val RED = Value(1, "red")
  val YELLOW = Value(2, "yellow")
  val BLUE = Value(3, "blue")
}
object ww extends App{
  println("aaaaaaa")
}