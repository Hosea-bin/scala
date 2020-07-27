package com.atguigu.day10

object Scala125_Transform5{
  def main(args: Array[String]): Unit = {
    //隐式类查找规则
    //1.当前代码的作用域中找到即可
    //2.当前代码的上级作用域
    //3.当前类所在的包对象
    //4.当前父类或者特质
    //5.如果想要隐式转换，直接导入
    val user = new User()
    user.insertUser()
    user.updateUser()

  }
  implicit class User06(user :User){
    def updateUser()={
      println("insert user...")
    }
  }


  class User{
    def insertUser()={
      println("insert user...")
    }
  }
}
