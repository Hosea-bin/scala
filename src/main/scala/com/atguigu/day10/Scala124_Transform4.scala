package com.atguigu.day10



object Scala124_Transform4{
  def main(args: Array[String]): Unit = {

  val user =new User()
    user.insertUser()
    user.updateUser
  }
  //隐式类
  implicit class UserExt(user:User){
    def updateUser={
      println("update user...")
    }
  }
  class User{
    def insertUser()={
      println("insert user...")
    }
  }
}
