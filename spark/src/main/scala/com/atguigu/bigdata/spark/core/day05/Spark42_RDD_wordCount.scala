package com.atguigu.bigdata.spark.core.day05

import org.apache.commons.lang3.mutable.Mutable
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object Spark42_RDD_wordCount
{
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(
      List(("hello", 33), ("hive", 43), ("spark", 22), ("spark", 11), ("hive", 10), ("hello", 21)),2
    )

    //aggregateByKey 方法 1
    val result1: RDD[(String, Int)] =
      rdd
        .aggregateByKey(0)(_+_,_+_)
    println(result1.collect().mkString(","))

    //foldByKey 方法 2
    val result2: RDD[(String, Int)] =
      rdd
        .foldByKey(0)(_+_)
    println(result2.collect().mkString(","))

    //groupByKey 方法 3
    val result3: RDD[(String, Int)] =
      rdd
        .groupByKey()
        .map { case (word, iter) => {
          (word, iter.sum)
        }
        }
    println(result3.collect().mkString(","))

    //reduceByKey 方法4
    val result4: RDD[(String, Int)] =
      rdd
        .reduceByKey(_+_)
    println(result4.collect().mkString(","))

    //combineByKey 方法5
    val result5: RDD[(String, Int)] =
      rdd
        .combineByKey(v => v,
          (t: Int, v) => (t + v),
          (t1: Int, t2: Int) => t1 + t2
        )
    println(result5.collect().mkString(","))

    //groupBy + map 方法六
    val result6: RDD[(String, Int)] =
      rdd
      .groupBy(_._1)
      .map { kv => {val count: Iterable[Int] = kv._2.map(kv => kv._2)
                   (kv._1, count.sum)
                   }
            }
    println(result6.collect().mkString(","))

//    //groupBy + mapValues 方法七
//    val result7: RDD[(String, Int)] =
//      rdd
//        .groupBy(_._1)
//        .mapValues(v => v.map(_._2).sum)
//
//    println(result7.collect().mkString(","))


    //countByKey 方法 8
    val result8: collection.Map[String, Long] =
      rdd
      .map(kv => (kv._1 + "-") * kv._2)
      .flatMap(_.split("-"))
      .map(word => (word, 1))
      .countByKey()
    println(result8)

    //countByValue 方法9
    val result9: collection.Map[String, Long] =
      rdd
      .map(kv => (kv._1 + "-") * kv._2)
      .flatMap(_.split("-"))
      .countByValue()
     println(result9)

    //aggregate  方法10
    val result10: mutable.Map[String, Int] =
      rdd
        .map(kv => (kv._1 + "-") * kv._2)
        .flatMap(_.split("-"))
        .aggregate(mutable.Map[String, Int]())(
        (map, s) => {
        map(s) = map.getOrElse(s, 0) + 1
        map
         },
          (map1, map2) => {
          map1.foldLeft(map2)((map, kv) => {
          map(kv._1) = map.getOrElse(kv._1, 0) + kv._2
          map
          })
        })
    println(result10)

    //fold  方法11
    val result11: mutable.Map[String, Int] =
      rdd
        .map(kv => (kv._1 + "-") * kv._2)
        .flatMap(_.split("-"))
        .map(word => mutable.Map(word -> 1))
        .fold(mutable.Map[String, Int]())(
          (map1, map2) => {
            map1.foldLeft(map2)(
              (map, kv) => {
                map.update(kv._1,map.getOrElse(kv._1,0)+kv._2)
                // map(kv._1) = map.getOrElse(kv._1, 0) + kv._2
                map
              }
            )
          }
        )

    val result12: mutable.Map[String, Int] =
      rdd
      .map(kv => (kv._1 + "-") * kv._2)
      .flatMap(_.split("-"))
      .map(word => mutable.Map(word -> 1))
      .reduce {
        (map1, map2) => {
          map1.foldLeft(map2)(
            (map, kv) => {
              map.updated(kv._1, map.getOrElse(kv._1, 0) + kv._2)
              // map(kv._1) = map.getOrElse(kv._1, 0) + kv._2
              //map
            }
          )
        }
      }

    println(result11)

println("fffff")

    println(result12)
    val re: Map[String, Int] = rdd
      .map(kv => (kv._1 + "-") * kv._2)
      .flatMap(_.split("-"))
      .map(word => Map[String, Int]((word, 1)))
      .reduce {
        (map1, map2) => {
          map1.foldLeft(map2)(
            (map, kv) => {
              map.updated(kv._1, map.getOrElse(kv._1, 0) + kv._2)
            }
          )
        }
      }
    println(re)

  }

}
