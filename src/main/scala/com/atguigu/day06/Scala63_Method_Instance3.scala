package com.atguigu.day06

object Scala63_Method_Instance3 {

    def main(args: Array[String]): Unit = {
   //面对对象-构造方法-构造顺序

      val user = new User("zhangsan",17)
    //
  }
  class User private(name:String){   //主构造方法私有化，外部无法使用
    println("AAAAA")

    def this()={
      this("lll")
      println("BBBB")
    }
    def this(name:String,age:Int){
      this()
      println("ccccc")
    }
  }
}

