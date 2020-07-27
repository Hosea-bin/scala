package com.atguigu.day03

object Scala18_Loop3 {
  def main(args: Array[String]): Unit = {
    val num = 9
//    for(i <-1 to 2*num by 2){
//      for(j <- 1 to i){
//        print("*")
//      }
//      println()
//    }

    for(i <- 1 to 2*num by 2;j = (18-i)/2){
      println(" "*j+"*"*i)
    }
    }
}
