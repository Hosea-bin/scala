package com.atguigu.day07

object Scala74_Interface4 {
  def main(args: Array[String]): Unit = {
  new User110
  }
}
//声明特质 /初始化顺序从左到右
trait Test111{
  println("aaaa")
}
trait Test112{
  println("bbbb")
}
trait Test113{
  println("cccc")
}
class User110 extends Test111 with  Test112 with Test113{
  println("dddd")
}