package com.atguigu.day03

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Scala29_Prictice2 {
  def main(args: Array[String]): Unit = {
   //声明一个函数，可将任意数字转换为任意的数据
    //创建一个定长为62的数组,并为数组元素赋值
    val arr = new Array[Char](62)
    //使用字符数组保存原始字符
    for(i<-0 to 9 ){
      arr(i)=(i+48).toChar
    }
    for(i<-10 to 35){
      arr(i)=(i+65-10).toChar
    }
    for(i<-36 to 61){
      arr(i)=(i+97-26-10).toChar
    }

    //定义集合存数据,随机生成6位的数据
    val buffer = new ListBuffer[String]()
    val random = new Random()
    var s : String = ""
    for(j<-1 to 8){
      s += arr(random.nextInt(arr.length))
    }
    buffer.append(s)

    def a (i:Int)={
      buffer
    }

    println(a(2))


  }
}