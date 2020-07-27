package com.atguigu.day07

object Scala69_Abstract2{
  def main(args: Array[String]): Unit = {
    new SubUser
    //println调用的是子类的age的get方法
    //println方法的位置在父类初始化时进行调用
    //父类初始化时，子类并未初始化。系统默认为0
  }
}

abstract class User1{
  val age :Int=20
  println(age)
}
class SubUser extends User1{
  override val age : Int = 28

}