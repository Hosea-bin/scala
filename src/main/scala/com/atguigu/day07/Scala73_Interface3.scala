package com.atguigu.day07

object Scala73_Interface3 {
  def main(args: Array[String]): Unit = {
    new SubUser1()

     /*
     aaa
     bbb
     ddd
     ccc
     eee
      */
    //接口与父子类无关
  }
}
//声明特质
trait Parent33{
  println("aaa" )
}
trait Test33 extends Parent33{
  println("bbb")
}
trait Test44 extends Parent33{
  println("ccc")
}
class Test55 extends Test33{
  println("ddd")
}
class SubUser1 extends Test55 with Test44{
  println("eee")
}