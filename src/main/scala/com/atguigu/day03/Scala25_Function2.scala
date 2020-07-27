package com.atguigu.day03

object Scala25_Function2 {
  def main(args: Array[String]): Unit = {
    //函数参数最大个数为22
    //声明的时候可以超过22，但是将函数作为对象赋值给变量时会报错

    //可变参数，相同类型的参数出现多次
    //java String...      scala String*
    def test(i : Int*):Unit={
      println(i)
    }
    test()
    test(1)
    test(1,2,4)

    //可变参数的顺序，放置在最后,只有一个可变参数
    def test1(name:String,i:Int*): Unit ={
      println("name:"+name,"i： "+i)
    }
    test1("dd",1,2,34,4)

    //Scala中参数默认使用val声明。无法进行修改
    //提供了参数默认值的语法解决参数默认值问题
//    def regular(name:String,password:String):Unit={
//      var pswd = ""
//      if(password==null){
//        pswd="000000"
//      }else{
//        pswd=password
//      }
//    }

      //参数默认值：参数声明时进行初始化
      def regUser(name:String,password:String="000000"):Unit={
        println("name="+name,"password="+password)
      }

      regUser("lisi")
      regUser("lisi","112222")

    def regUser1(name:String,password:String="000000",tel:String):Unit={
      println("name="+name,"password="+password,"tel="+tel)
    }
    regUser1("zhangsan","000231","2399393")
    regUser1("zhangsan",tel = "1992828")


  }
}