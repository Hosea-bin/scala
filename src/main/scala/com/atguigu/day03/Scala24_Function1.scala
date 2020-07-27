package com.atguigu.day03

object Scala24_Function1 {
  def main(args: Array[String]): Unit = {
    //1.无参，无返回值
    def fun1() : Unit={
     println("wfaf")
    }
   // fun1()
   println(fun1())
    //2.无参有返回值
    def fun2() : String = {
      return "huhu"
    }
  //  println(fun2())
    }
    //3.有参，无返回值
   def fun3(s : String):Unit = {
     println( " name " + s)
   }
  fun3("hubin")
  //println(fun3("huhu"))
    //4.有参有返回值
  def fun4(name : String):String={
    return "name = "+name
    }

  println(fun4("libai"))

  //多参，无返回值
  def fun5 (name :String,age :Int):Unit={
    println(
      s"""
        |{"name":"$name","age":$age}
        |""".stripMargin)
    println(s"name=$name , age=$age")
  }
  fun5("zhangsan",33)
  //多参，有返回值
  def fun6 (name :String,age:Int):String={
    return s"name=$name , age=$age"
  }

  println(fun6("lisi", 22))
}