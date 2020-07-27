package com.atguigu.day09

object Scala107_Collection_Method5 {

  def main(args: Array[String]): Unit = {
    //集合常用方法--折叠

    //将集合之外的数据和集合内部数据进行聚合操作，称之为折叠
    val dataList = List(1,2,3,4)

    //fold方法存在函数柯里化，两个参数上列表
    //第一个参数列表 => z:A1 [z:zero ,表示数据处理初始值]
    //第二个参数列表中参数 => (A1,A1)=>A1 [聚合数据的逻辑]
    val i: Int = dataList.fold(8)(_-_)
    println(i)
    //从源码角度来说fold底层就是foldleft
     val str: String = dataList.foldLeft("")(_+_)
    println(str)
    val i1: Int = dataList.foldRight(3)(_-_)
    println(i1)
  }
}
