package com.atguigu.day05

object Scala46_Object {
  def main(args: Array[String]): Unit = {
    //面向对象
    val dept : Dept=new Dept()
    val user : User01=new User01()
    user.dept=dept
    user.name="lisi"
    user.test()
    println(s"用户${user.name}所在的部门${user.dept.name}")
  }
}
class Dept{
  var name : String = "开发部门"
}
class User01{
  var dept : Dept =null
  var name : String = "zhangsan"

  def test()={
    println("user test...")
  }
}