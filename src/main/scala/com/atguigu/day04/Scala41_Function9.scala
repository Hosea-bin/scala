package com.atguigu.day04

import com.atguigusummer.framework.core.TApplication

object Scala41_Function9 {
  def main(args: Array[String]): Unit = {
    //惰性函数  延迟加载
    def test():String = {
      println("xxxxxx")
      "zhangsan"
    }

    lazy val name = test()
    println("*********")
    println("name ="+name)


  }

}
