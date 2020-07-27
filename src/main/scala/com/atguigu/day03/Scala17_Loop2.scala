package com.atguigu.day03

object Scala17_Loop2 {
  def main(args: Array[String]): Unit = {
    for(i <-Range(1,5);j<-Range(1,5)){
     //println("i="+ i + ",j ="+j)
      println("i="+i,"j="+j)
    }
    }
}
