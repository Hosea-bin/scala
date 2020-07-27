package com.atguiu

import java.io.{FileWriter, PrintWriter}

import scala.io.Source
import scala.reflect.api.Printers

object Scala11_Out {
  def main(args: Array[String]): Unit = {
   //输出
    val out=new PrintWriter(
      new FileWriter("output/test.txt")
    )
    out.println("lisi")
    out.flush()
    out.close()
  }
}
