package com.atguigu.day10

object Scala115_Match3{
  def main(args: Array[String]): Unit = {

    //使用Case关键字声明的类，称为样例类
    //样例类编译时，自动生成伴生对象以及apply方法
    //样例类的构造参数默认使用val声明，参数就是类的属性，属性可改，应用var声明
    //样例类会自动生成unapply方法
    //样例类自动实现可序列化接口
    //实际开发中，一般都使用样例类，便于开发
    val emp: Any = Emp("lisi",20)
    emp match {
      case Emp("lili",30) =>println("xxxxx")
      case _=>println("yyyyy")
    }

    //匹配对象
      //Scala模式匹配对象会自动调用对象的unapply方法
    //匹配对象，其实匹配的是对象的属性是否相同
    val user = User("zhangsan",20)
    val result: String = user match {
      case User("zhangsan", 10) => "yes"
      case _ => "no"
    }
    result
    println(result)
  }
  //声明样例类
  case class Emp(var name :String,age:Int)
  //声明伴生类
  class User(val name :String,val age:Int)
  //声明伴生对象
  object User{
    //使用参数自动构建对象
    def apply(name: String, age: Int): User = new User(name, age)
    //使用对象自动获取参数
    def unapply(user: User): Option[(String, Int)] = {
      Option((user.name,user.age))
    }
  }
}
