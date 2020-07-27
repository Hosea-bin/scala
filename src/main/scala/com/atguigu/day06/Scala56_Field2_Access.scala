package com.atguigu.day06

object Scala56_Field2_Access {

    def main(args: Array[String]): Unit = {

      //访问权限
      /*
      private : 私有权限，同类
      private [包名]: 包私有权限，同类，同包
      protected :受保护权限。子类
      （default）:公共权限
       */

      //private :同类
      val user = new User
      user.test()

      //private [包名]: 包私有权限，同类，同包
      //中括号可以向上使用

      // protected :受保护权限。同类。子类，

      //公告的，哪都可以用
  }

  //内部类
  class User{
    private var name : String = "张三"
    private[day06] var age:Int=67
    private[atguigu] var sex:String="male"
    protected var tel:String = "13123123"

    def test()={
      println("name : "+name)
      println("age : "+age)
      println("sex : "+sex)
      println("tel :"+tel)
    }
  }

  class SubUser extends User {
    def testSub()={
      println(this.tel)
      println(this.age)
    }
  }

  class Emp {
    def test()={
      val user = new User()
      user.age  //同一个包中
      user.sex

    }
  }
}

