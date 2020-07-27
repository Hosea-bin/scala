package com.atguigu.day07

object Scala67_Test1{

  def main(args: Array[String]): Unit = {
    //单例模式
    //  //1.构造方法私有化
    //  //2.使用静态方式获取指定类型对象,加括号调用了pply方法
    val dept1=Dept()
    val dept2=Dept()

    println(dept1 eq dept2)
    println(dept1)
    println(dept2)
  }
}

class Dept private{
//
}
//伴生对象只会初始化一次，所以是单例对象
//让伴生对象初始化时创建一次，则是单例
object Dept{
  val dept = new Dept()
  def apply(): Dept = {
    dept
  }
}