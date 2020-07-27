package com.atguiu

import scala.io.Source

object Scala10_In1 {
  def main(args: Array[String]): Unit = {
    val strings :Iterator[String]= Source.fromFile("input/pom.xml").getLines()
    while (strings.hasNext){
      println(strings.next())
    }
  }
}
