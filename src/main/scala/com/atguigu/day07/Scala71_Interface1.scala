package com.atguigu.day07

object Scala71_Interface1 {
  def main(args: Array[String]): Unit = {
   //1.不仅有抽象方法，还有具体方法
    val mySQL = new MySQL1 with Operate1
    mySQL.insert
    mySQL.select
  }
}
trait Operate1{
  def insert={
    println("insert data")
  }
}
class MySQL1{
  def select={
    println("select data")
  }
}