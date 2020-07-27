package com.atguigu.day02

object Scala13_Flow2 {
  def main(args: Array[String]): Unit = {
  //Scala - 分支
    val age = 50

    val result = if(age<40){
      20
    }else {
      //throw new Exception//int
      "aaa"
    }
    println(result)//AnyVal
    }
}
