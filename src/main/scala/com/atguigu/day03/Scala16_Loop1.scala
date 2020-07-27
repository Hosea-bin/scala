package com.atguigu.day03

object Scala16_Loop1 {
  def main(args: Array[String]): Unit = {
    //Scala循环， 循环时可以增加条件来决定是否继续循环体的执行,这里的判断条件我们称之为循环守卫
    for(i <- Range(1,5) if i != 4){
      println("i="+i)
    }
    }
}
