package com.atguigu.day02

object Scala11_Flow {
  def main(args: Array[String]): Unit = {
    //Scala - 分支
    val age = 45
    if(age<70){
      println("age<70")
    }
    //双分支
    if(age<20){
      println("age<20")
    }else{
      println("age>=20")
    }

    //多分支
    if(age<18){
      println("未成年")
    }else if(age<38){
      println("青年")
    }else if(age<55){
      println("中年")
    }else{
      println("老年")
    }
  }
}
