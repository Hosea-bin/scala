package com.atguigu.day08

import scala.collection.mutable.ArrayBuffer

object Scala83_Collection_Array5 {

  def main(args: Array[String]): Unit = {

    val array = Array(1, 2, 34, 4)

  /*  array.foreach((i: Int) => {
      println(i)
    })
    array.foreach(i => println(i))
    array.foreach(println(_))
    array.foreach(println)
    */
    var myMatric =Array.ofDim[Int](3,3)
   // myMatric.foreach(list=>list.foreach(println))

    val arr1 =Array(1,2,3,4)
    val arr2 =Array(5,6,7,8)

    val arr6: Array[Int] = Array.concat(arr1,arr2)
    println(arr6.mkString(","))

    val arr7: Array[Int] = Array.range(0,6)
    println(arr7.mkString(","))

    val arr8: Array[Int] = Array.fill[Int](5)(7)
    println(arr8.mkString(","))
    class TestArray{
      val buffer = new ArrayBuffer[Int]()
      def foreach(f:Int => Int)={
        for(i <- buffer){
          f(i)
        }
      }
    }

    object TestArray{
      def apply(is:Int*): TestArray ={
        val array = new TestArray()
        for(i <- is){
          array.buffer.append(i)
        }
        array
      }
    }
  }

}
