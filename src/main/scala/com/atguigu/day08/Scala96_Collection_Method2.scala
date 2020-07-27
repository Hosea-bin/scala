package com.atguigu.day08

object Scala96_Collection_Method2{

  def main(args: Array[String]): Unit = {

   //常用方法
    //map方法可以将集合通过指定的转换规则变成新的集合
     val list = List(1,2,3,4)
   val newList: List[Int] = list.map(_*2)
   println(newList)

    //扁平化，就是将整体拆分成一个个的个体使用的方法
    //默认只能对最外层数据进行操作
    val list1=List(
      List(
        List(1,2)
      ),List(
        List(3,4)
      )
    )

    println(list1.length)
    println(list1)
    println(list1.flatten)
    println(list1.flatten.flatten)

    //扁平映射=扁平化+映射
    val list2 =List(
      List(1,2),List(3,4)
    )
    println(list2.flatten.map(_*2))
    println(list2.flatMap(List=>List.map(_*3)))
    println(list2.flatMap(_.map(_*3)))

    val list3 =List("hello scala","hello spark")
    println(list3.flatten)
    println(list3.flatMap((s:String)=>s.split(" ")))
    println(list3.flatMap(_.split(" ")))

    val list4 = List(1,2,3,4)
    println(list4.flatMap((num) => List(num * 2)))
    println(list4.flatMap((num) => List(num * 2)) eq list4)

    //过滤
    val list5 = List(1,2,3,4)
    println(list5.filter(num => num % 2 == 0))

    val list6 =List("hello scala","solo spark")
    println(list6.filter((word:String) =>word.startsWith("s")))

    val list7 = List(1,2,3,4)
    val map: Map[Boolean, List[Int]] = list7.groupBy(num=>num%2==0)
    println(map)
    //分组的数据类型是Map
    val list8 = List("hello","hello","scala","lili")
    println(list8.groupBy(word=>word))

    //排序
    val list9 =List(2,5,3,1)
    println(list9.sortBy(num => num))
    println(list9.sortBy(num => -num))
    println(list9.sortBy(num => num).reverse)
    println(list9.sortBy(num => num)(Ordering.Int.reverse))//函数柯里化

    val list10=List("1","4","7","3","2")
    println(list10.sortBy(s =>s.toInt))

    //自定义数据排序
    val user1 = new User()
    user1.age=20
    user1.name="zhangsan"

    val user2 = new User()
    user2.age=29
    user2.name="lisi"

    val user3 = new User()
    user3.age=20
    user3.name="wangwu"

    val list11 = List(user1,user2,user3)
    println(list11.sortBy(user=>(user.age,user.name)))

  }
}
class User(){
  var name :String = _
  var age :Int= _

  override def toString: String = {
    s"User[$name,$age]"
  }
}