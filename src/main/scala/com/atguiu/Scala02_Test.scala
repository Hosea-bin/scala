package com.atguiu

object Scala02_Test {
    //声明方法def
    //名称(参数列表)：类型
    //参数名：参数类型
    //赋值
    def main(args : Array[String] ): Unit={
      //方法体
      println("Hello Test")
      Scala02_Test.test("rwrrwr");
     // println(Scala02_Test.test("rwr"));
    }

    def test(args : String):String={
      println("abcde");
      return "args";
    }
}
