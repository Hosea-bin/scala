package com.atguigu.day11

object Scala128_Generic1 {
  def main(args: Array[String]): Unit = {
    def f[A : Test](a: A) = println(a)
    implicit val test : Test[User] = new Test[User]
    f( new User() )
  }
  class Test[T] {
  }
  class Parent {
  }
  class User extends Parent{
  }
  class SubUser extends User {
  }
}
