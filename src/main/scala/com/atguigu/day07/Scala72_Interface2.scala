package com.atguigu.day07

object Scala72_Interface2 {
  def main(args: Array[String]): Unit = {

  }
}
//声明特质
trait ParentTest{

}
trait ParentTest1{

}
trait Test extends  Exception with ParentTest  with ParentTest1{
  //抽象方法
  def test():Unit
  //具体方法
  def test1()={
    println("test")
  }
}
class User3 extends java.io.Serializable{  //java接口
  def test()={
  }
}

abstract class User4 extends Test {
  def test():Unit
  //具体方法
   override def test1()={
    println("test")
  }
}