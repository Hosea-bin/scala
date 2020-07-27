package com.atguigu.day07

object Scala77_Collection_Array {
  def main(args: Array[String]): Unit = {
  val array = new Array[String](4)
  array(0)="lili"
    array(1)="sisi"
    array(2)="didi"
    array(3)="fifi"
    array(1)="sisi"

    val a: Array[String] = array :+ "ssss"
    for(s <-a){
      println("s= "+s)
    }
    println(a.mkString(","))

  }
}
