package com.atguigu.day10

object Scala116_Match4{
  def main(args: Array[String]): Unit = {

    //应用
    val list = List(("a",1),("b",2),("c",3))
    val newList: List[(String, Int)] = list.map(
      (t) => {
        (t._1, t._2 * 2)
      }
    )
    newList
    ////模式匹配时
    val newList1: List[(String, Int)] = list.map {
      case (word, count) => {
        (word, count * 2)
      }
    }
    newList1
    println(newList1)

    //模式匹配时，小括号需要变成大括号
    //case后面的小括号表示元组

    val tuples: List[(String, Int)] = list.filter {
      case (_, count) => {
        count == 2
      }
    }
    println(tuples)
    val ints: List[Int] = list.flatMap {
      case (word, count) => {
        List(count)
      }
    }
    println(ints)

    //集合元素
    val map: Map[String, (String, Int)] = Map("a"->("a",1),"b"->("bb",2))
    map.foreach{
      case(_,(_,count))=>{
        println(count)
      }
    }

    //元组
    val (id,name,age)=(1,"zhangsan",30)
    val t=(1,"zhangsan",30)
    println(t._3)
  }
}
