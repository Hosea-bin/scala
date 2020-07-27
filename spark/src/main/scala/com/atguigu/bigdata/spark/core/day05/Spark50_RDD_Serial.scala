package com.atguigu.bigdata.spark.core.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark50_RDD_Serial {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    //序列化
    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))
//    rdd.foreach(
//      num =>{
//        val user = new User()
//        println("age = "+(user.age+num))
//      }
//    )
    //如果算子中使用了算子外的对象，那么在执行时，需要保证这个对象能序列化
    val user = new User()
    rdd.foreach(
      num =>{
        println("age = "+(user.age+num))
      }
    )
    //Scala闭包，spark算子的操作其实都是闭包，所以闭包可能包含外部变量
    //当闭包包含外部变量，则一定保证外部变量可序列化
    //Spark在提交作业之前，应该对闭包内的变量进行检测，检测是否可以序列化
    //该操作称为闭包检测


    sc.stop()
  }
//  class User extends Serializable {
//    val age:Int =20
//  }
  //样例类自动混入可序列化特质
  case class User(age : Int =20)
}
