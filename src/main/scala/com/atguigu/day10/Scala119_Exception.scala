package com.atguigu.day10

object Scala119_Exception{
  def main(args: Array[String]): Unit = {
    //Scala异常处理
    //1.catch关键字只使用一次
    //2.多个异常采用case语法进行区分
    //3.异常处理的语法类似模式匹配
    //4.Scala中异常不区分编译器异常时异常和运行时异常，无需处理和抛出
    //java包装类型的数据比较用equals，异常捕捉顺序，先小后大
    //java在调用scala对象时，并没有明确的异常处理

    try{
      val i =0
      val j=10/0
    }catch {
      case e:ArithmeticException=>{
        println("ArithmeticException...")
      }
      case e: Exception => {
        e.printStackTrace()
      }
    }finally {

    }
    }
}
