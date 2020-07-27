package com.atguigu.day10

object Scala123_Transform3{
  def main(args: Array[String]): Unit = {

   def regUser(name:String)(implicit password:String="123123")={
     println(s"注册用户：$name,默认密码：$password")
   }
    //隐式变量
    implicit val pswd:String="88888888"

    regUser("zhangsan")("6666666")

    val list = List(1,2,3,4)
    list.sortBy(num=>num)(Ordering.Int.reverse)

    val s="abc"
    println(s(0))
  }
}
