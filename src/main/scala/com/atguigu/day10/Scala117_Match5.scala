package com.atguigu.day10

object Scala117_Match5{
  def main(args: Array[String]): Unit = {
  val Array(first,second,_*) = Array(1,7,2,9)
    println(first)
    val Person(name,age)=Person("zhangsan",16)
    println(name)
    val map=Map("A"->1,"B"->0,"C"->2)
    for(kv<-map){
      println(kv._1)
    }

    for((k,0)<-map){
      println(k,0)
    }
  }

  case class Person(str: String, i: Int)
}
