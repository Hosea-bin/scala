package com.atguigu.day02

import java.util
object Scala03_DataType2{
  def main(args: Array[String]): Unit = {
    //Scala将null作为一个特殊的对象进行处理，类型就是Null ,Null是子类型
    val a = null
    val s : String = null
    //注意Null不能对AnyVal类型进行赋值

    //Nothing 没有值
    //Nil空集合
    val nil : List[Nothing] =Nil
    val k : Any ="2313"
    val l :AnyVal=2
    val o : AnyRef ="eqe"

  }
  def test() ={
    throw new Exception
  }
}
