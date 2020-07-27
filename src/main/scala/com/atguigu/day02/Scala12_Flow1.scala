package com.atguigu.day02

object Scala12_Flow1 {
  def main(args: Array[String]): Unit = {
  val age = 28
    //返回值取满足条件的最后一行代码
    val result = if(age<18){
      "未成年"
    }else if(age<36){
      "青年"
      println()
    }else if(age<55){
      "中年"
    }else {
      "老年"
    }
    //方法的返回值类型是Unit，Unit 的返回对象结果是（），此时result类型是Any

    println("结果:"+result)
  }
}
