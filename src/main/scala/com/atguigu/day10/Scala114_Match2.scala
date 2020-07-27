package com.atguigu.day10

object Scala114_Match2 {
  def main(args: Array[String]): Unit = {
  //匹配元组

    for(tuple<-Array(
        (0,1),
        (1,0),
        (1,1),
        (1,0,2))){
      val result: String = tuple match {
        case (0, _) => "0 ..."
        case (y, 0) => "" + y + "0"
        case (a, b) => "" + a + "0" + b
        case _ => "something else"
      }
      result
      println(result)
    }
  }
}
