package com.atguigu.day09

object Scala106_Collection_Method4 {

  def main(args: Array[String]): Unit = {

  val list = List(1,2,3,4)
//    //val i: Int = list.reduce(
//      (a: Int, b: Int) => {
//        a + b
//      }
//    )
    val i: Int = list.reduce((a,b)=>a+b)
    println(i)

    val i1: Int = list.reduceLeft(_-_)
    val i2: Int = list.reduce(_+_)
    val i4: Int = list.reduce(_*_)
    println(i4)
    println(i1)
    println(i2)

    val i3: Int = list.reduceRight(_-_)
    println(i3)
  }
}
