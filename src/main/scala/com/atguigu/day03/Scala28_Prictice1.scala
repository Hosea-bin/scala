package com.atguigu.day03

object
Scala28_Prictice1 {
  def main(args: Array[String]): Unit = {
   //声明一个函数，可将任意数字转换为其两倍
    def enlarge(x : Int)= x * 2
    //val f=enlarge _
   // println(f(9))
    println(enlarge(9))


    def transform2(num:Double,f:(Double)=>Any):Any={
      f(num)
    }

    val result = transform2(num = 5,(d:Double)=>{"Number is" +d})
    println(result)
  }
}