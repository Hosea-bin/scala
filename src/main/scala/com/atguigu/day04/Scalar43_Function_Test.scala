package com.atguigu.day04

object Scalar43_Function_Test {
  def main(args: Array[String]): Unit = {

    //将数字A转换为任意的数据B,转换规则自己定义
    def transform(num:Double,f:(Double)=>Any)={
      f(num)
    }
    val result = transform(5,(f:Double)=>"Number is "+f)
    println(result)


    //test(10,20,c)的计算结果有参数c来决定
    def test(x:Int,y:Int,c:(Int,Int)=>Any)={
      c(x,y)
    }
    println(test(10,10,(x:Int,y:Int)=>(x+y)))
    println(test(10,10,(x,y)=>x+y))
    println(test(10,20,_+_))

    //设计一个过滤函数
    def filter(s:String,fun:(String)=>Boolean)={
      val words:Array[String]=s.split(" ")
      //val strings:Array[String] = s.split(" ")
      var result = " "
      for (word <- words){  //对数据集Words进行循环
        if(fun(word)){
          result += ", "+word
        }
      }
      result.substring(2)
    }

    println(filter("hello word scala spark", _.startsWith("s")))
    //中括号省略，类型省略。使用一次下划线替代
    //filter("hello word scala spark", (word:String) =>{word.startsWith("s")})
    filter("hello word scala spark", (word:String) =>word.startsWith("s"))
    filter("hello word scala spark", word =>word.startsWith("s"))
  }
}
