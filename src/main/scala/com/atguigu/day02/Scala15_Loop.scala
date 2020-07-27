package com.atguigu.day02

object Scala15_Loop {
  def main(args: Array[String]): Unit = {

    //Scala循环
//    for(i <- 1 to 5){
//      println("i =" + i)
//    }
//
//    for(i <- 1 until(6)){
//      println("i="+i)
//    }
//
//    for(i <- Range(1,4)){
//      println("s="+i)
//    }
    for(i <- Range(1,8,2)){
      println("i="+i)
    }

    for (i <- 1 to 8 by 3){
      println("i="+i)
    }
    }
}
