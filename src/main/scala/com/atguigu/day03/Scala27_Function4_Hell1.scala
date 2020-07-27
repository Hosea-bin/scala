package com.atguigu.day03

object Scala27_Function4_Hell1 {
  def main(args: Array[String]): Unit = {
  //2.函数可以作为参数传递给其他函数
    def fun2(i:Int):Int={
      i*2
    }

    def test2(f:(Int)=>Int)={
      f(10)
    }

    val f=fun2 _
    println(test2(f))
    println(test2(fun2))

    //2.2将函数作为参数使用时，一般不关心函数的名称，则使用匿名函数

//    val result=test2((i:Int)=>{i*i})
//    println(result)
    //至简原则
    val result=test2((i:Int)=>i*i)
    val result2=test2(i=>i*2)

    //如果匿名函数中的参数在逻辑代码中只使用了一次，则参数=>可以省略

    val result3=test2(_*2)
    println(result3)
  }
}