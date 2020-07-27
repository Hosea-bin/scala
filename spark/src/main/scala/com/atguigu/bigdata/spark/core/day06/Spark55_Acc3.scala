package com.atguigu.bigdata.spark.core.day06

import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Spark55_Acc3 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)
    // wordCount
    val rdd: RDD[String] = sc.makeRDD(List("hello scala","spark hello"))
    //声明累加器
    val acc=new WordCountAccumulator
    //注册累加器
    sc.register(acc,"sum")
    //使用累加器
    rdd.flatMap(_.split(" "))foreach(
      word=>{
        acc.add(word)
      }
    )
    //获取累加器的值
    println("sum"+acc.value)

    sc.stop()
  }
  //自定义累加器
  //继承 Accumulator2,定义泛型[IN,OUT]
  class WordCountAccumulator extends AccumulatorV2[String,mutable.Map[String,Int]]{
    //存储wordCount的集合
    var map: mutable.Map[String, Int] = mutable.Map()
    //累加器是否初始化
    override def isZero: Boolean = {
      map.isEmpty
    }
    //复制累加器
    override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = {
      new WordCountAccumulator
    }
    //重置累加器
    override def reset(): Unit = {
      map.clear()
    }
    //向累加器中增加值
    override def add(word: String): Unit = {
      map(word)=map.getOrElse(word,0)+1
    }
    //合并当前累加器和其他累加值
    override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
      val map1=map
      val map2=other.value
      map=map1.foldLeft(map2){
        (map,kv)=>{
          map(kv._1)=map.getOrElse(kv._1,0)+kv._2
          map
        }
      }
    }
    //返回累加器的值（Out）
    override def value: mutable.Map[String, Int] = map
  }
}
