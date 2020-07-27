package com.atguigu.day03

object Scala27_Function4_Hell {
  def main(args: Array[String]): Unit = {
  //函数可以作为对象赋值给变量
    def test1()="zhangsan"
    def test11(i:Int):Int={
      i*2
    }
   //val f1 = test1 _
    val f1 :()=> String =test1
  //  val ffi : (Int)=>Int=test11
    val ffi=test11 _
    //println(f1())
    println(ffi(10))
  }
}