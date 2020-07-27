package com.atguigu.day10

object Scala118_Match6{
  def main(args: Array[String]): Unit = {
    //偏函数
    //所谓的偏函数就是对满足条件的一部分数据进行处理的函数
    //map函数不支持偏函数，支持全量函数操作
    val list1 = List(1,2,"3",4)
    //声明偏函数使用模式匹配进行数据处理

    val pf:PartialFunction[Any,Any]={
      case i:Int =>i*2
      case s:String =>s
      }
    //调用支持偏函数的函数
    //collect:采集，支持偏函数
    val list: List[Any] = list1.collect(pf)
    println(list)

    //偏函数一般可以用模式匹配代替
    val list3=List(1,2,3,"4")
    val newList: List[Any] = list3.collect {
      case i: Int => i + 1
      case s: String => s
    }
    println(newList)

    println(list3.filter(_.isInstanceOf[Int]).map(_.asInstanceOf[Int]+1))
    }
    //不使用偏函数

}
