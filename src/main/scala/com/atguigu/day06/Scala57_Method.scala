package com.atguigu.day06

object Scala57_Method {

    def main(args: Array[String]): Unit = {
      //方法就是类中声明的函数
      //scala中的方法
      val user = new User

      val clazz  = user.getClass //获取对象的类型信息
      val unit = classOf[User]
  }

  class User{
    override def equals(obj: Any): Boolean = {
      if(obj.isInstanceOf[User]){
        val otherUser = obj.asInstanceOf[User]
        this==otherUser
      }else{
        false
      }
    }
  }
}


