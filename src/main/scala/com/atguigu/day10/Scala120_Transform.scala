package com.atguigu.day10

object Scala120_Transform{
  def main(args: Array[String]): Unit = {
    //Todo 隐式转换
    //Java中基本类型的数值之间存在精度的转换和截取
    //Scala中没有精度的概念，编译时会自动由编译器调用java逻辑进行数据操作
    //两个类型如果需要互相转换，则必须存在关系：
    //父子类
    //接口和实现类

    //本来两个类型之间不存在关系，无法进行类型转换，但是编译器在编译时，尝试找到了对应的转换方法将类型转换
    //尝试找到对应转换方法将类型进行转换，让程序编译通过
    //这个自动转换的过程称为隐式转换。由编译器完成，称为二次编译
    val b:Byte=10
    val i:Int=b
    println(i)
  }
}
