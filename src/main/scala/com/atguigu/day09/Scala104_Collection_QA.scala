package com.atguigu.day09

object Scala104_Collection_QA {

  def main(args: Array[String]): Unit = {

    //匿名函数下划线的作用
    //1.代替参数出现一次，直接使用下划线
    //2.下划线可以将函数作为整体进行传递
    //3.如果匿名函数的参数不参与逻辑处理，可以使用下划线省略

    //匿名函数中逻辑处理直接返回参数本身，则不用下划线处理
    val list = List("hello","hello","lisa")
    val stringToStrings: Map[String, List[String]] = list.groupBy(_=>"s")
    val stringToStrings1: Map[String, List[String]] = list.groupBy(word=>word)
    println(stringToStrings)
    println(stringToStrings1)

    val dataList = List(List(3,4),List(5,6))
    println(dataList.flatMap(list=>list))
    val list1: List[Any] = List(1,2,List(3,4))

//    val list2: List[Any] = list1.flatMap(
    ////      data => {
    ////        //模式匹配
    ////        if (data.isInstanceOf[List]) {
    ////          data.asInstanceOf[List]
    ////        } else {
    ////          List(data)
    ////        }
    ////      }
    ////    )
    ////    println(list2)

    val strings = List("3","6","1","4")
    println(strings.sortBy(s=>s)(Ordering.String))
    val tuples: List[(Int, String)] = List((20,"lisi"),(3,"wangwu"),(24,"zhangsan"))
    println(tuples.sortBy(t =>t )(Ordering.Tuple2(Ordering.Int.reverse,Ordering.String)))

    //自定义排序
    println(tuples.sortWith(
      (left,right)=>{
        if(left._1<right._1){
          true
        }else if(left._1==right._1) {
          left._2 < right._2
        }else{
          false
        }
      }
    ))
  }
}
