package com.atguigu.day07

import scala.beans.BeanProperty

object Scala68_Abstract1{

  def main(args: Array[String]): Unit = {
      val user = new User()
         println(user.name)
    println(user.age)
//    user.test()
//    println(user)
//    user.name="laili"
//    println(user.name)
//    new User()
    println(user.test1())
    println(new User().test1())
//    5val ints = Array(1,2,3)
//    val array: Array[Any] = ints :+ "5"
  }
}


abstract class Person{
  def test():Unit  //抽像方法
  var name:String
  @BeanProperty
  val age : Int =20
  def test1()={
    println(age)
  }
  //println(age) //age
  // 属性的get方法，成员方法，动态绑定
}
class User extends Person{
  override def test(): Unit = {
    println("zhangsan")
  }
  //子类重写父类完整方法、属性，需要显示增加override关键字修饰
  //子类重写父类抽像方法、属性，需直接补充完整即可，或采用override关键字
  var name: String = "lisi"
  @BeanProperty
  override val age: Int = 90
  //println(age)
}