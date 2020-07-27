package com.atguigu.day10

object Scala113_Match1 {
  def main(args: Array[String]): Unit = {

    //匹配常量
    def describe(x:Any):Any={
      val result = x match {
        case 5 => "Int five"
        case "hello" => "String hello"
        case true => "Boolean true"
        case '+' => "Char +"
      }
      println(result)
    }
    describe(true)

    //匹配类型
    //下划线的作用是省略参数，因为逻辑中不使用参数，可省略
    //需要参数，可以起名字
    //类型匹配不考虑泛型，数组的泛型是类型的一部分
    def describe1(x: Any) = x match {
      case i: Int => "Int"
      case s: String => "String hello"
      case m: List[_] => "List"
      case c: Array[Int] => "Array[Int]"
      case someThing => "something else " + someThing
    }
    println(describe1("sd"))
    val ints = Array(1,2,3)
    println(describe1(ints))

    //匹配数组
    for(arr <-Array(
      Array(0),
      Array(0,1),
      Array(1,0,2),
      Array(1,2,1,0),
      Array("hello",1,2,3)
    )){
      val result: String = arr match {
        case Array(0) => "0"
        case Array(x, y) => x + "," + y
        case Array(1, _*) => "以数字1开头的数组"
        case _ => "something else"
      }
      result
    println("result:"+result)
    }


    //匹配列表
    val list: List[Int] = List(1,2,3,4,5)
    val list2: List[Int] = List(1,2)
    val list3 = List(2)
    list2 match {
      case first ::second ::rest =>println(first+" "+second+" "+rest)
      case _ =>println("something else")
    }

    for(list<-Array(
      List(0),
      List(1,2,3,4),
      List(0,0,0,0),
      List(8,8))){
        val result: String = list match {
          case List(0) => "0"
          case List(x, y) => x + "," + y
          case List(0, _*) => "0..."
          case _ => "something else"
        }
        println(result)
      }

    val list1 = List(1,"2",3,List(2,4))
    val result2: List[Any] = list1.flatMap(data => {
      data match {
        case a: List[_] => a
        case b => List(b)
      }
    })
    result2
    println(result2)

  }
}
