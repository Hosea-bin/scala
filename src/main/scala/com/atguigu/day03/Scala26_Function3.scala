package com.atguigu.day03

object Scala26_Function3 {
  def main(args: Array[String]): Unit = {
   //函数式编程
    def test(name:String):String={
      return "name ="+name
    }

    println(test("ewew"))
    //1.可以省略return关键字
    def test1(name:String):String={
       "name ="+name
    }

    println(test1("sfs"))
    //2.如果编译器能推断函数的返回值类型，那么返回值类型可以省略
    def test2(name:String)={
      "name ="+name
    }

    println(test2("wrrw"))

    def test3(name:String)={
      val age = 20
      if(age<20){
        "lisi"
      }else{
        233
      }
    }

    println(test3("eee"))

    //如果函数逻辑只有一行代码，则大括号可以省略
    def test4(name:String)= "name ="+name

    println(test4("fs"))

    //如果函数没有提供参数，那么调用的时候，小括号可以省略
    def test5()="ljdhhd"
    def test6="lj"

    println(test5())
    println(test5)
    println(test6)

    //函数明确使用Unit声明，则函数体return关键字不起作用
    //过程函数
    def test7():Unit={
      return "zhangsan"
    }
    def test8(){
      return "zhangsrrrrrran"
    }
    println(test7())
    println(test8())

    //没有名称和和def关键字的函数称为匿名函数
    //（参数列表）=> {代码逻辑}
    val a=() => println("no name")
    //调用
    a()
  }
}