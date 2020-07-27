package com.atguigu.day06

object Scala48_Import1 {

    def main(args: Array[String]): Unit = {
      val user02=new User02()
      import user02._

      test1()
      login()
    }
  }

class User02{
  def test1()={
    println("gdghadg")
  }

  def login()={
    println("dahdh")
  }
}