package com.atguigu.day09

import scala.collection.immutable
import scala.collection.parallel.immutable.ParSeq

object Scala111_Collection_Test {

  def main(args: Array[String]): Unit = {
  //不同省份商品点击排行
    val dataList = List(
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "电脑"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "电脑"),
      ("zhangsan", "河南", "电脑"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子")
    )
    dataList
    //数据存在多余，先进行数据的清洗
    val list: List[String] = dataList.map(
      t => {
        (t._2 + "-" + t._3)
      }
    )
    list
    println(list)
    //统计数据，根据省份和商品同时进行分组
    val dataToListMap: Map[String, List[String]] = list.groupBy(data=>data)
    println(dataToListMap)

    val dataToCountMap: Map[String, Int] = dataToListMap.mapValues(_.size)
    println(dataToCountMap)
      //将分组后的数据进行结构转换
      //
//    val tuples: Map[String, (String, Int)] = dataToCountMap.map(kv => {
//      val k = kv._1
//      val count = kv._2
//      val ks: Array[String] = k.split("-")
//      (ks(0), (ks(1), count))
//    })
//    tuples
//    tuples
//    println(tuples)
//    val stringToStringToTuple: Map[String, Map[String, (String, Int)]] = tuples.groupBy(_._1)
//    println(stringToStringToTuple)
    //改变数据结构可能会导致key重复，那么不要使用map结构
    val proToItemAndCountList: List[(String, (String, Int))] = dataToCountMap.toList.map(kv => {
      val k = kv._1
      val count = kv._2
      val ks = k.split("-")
      (ks(0), (ks(1), count))
    })
    proToItemAndCountList

    println(proToItemAndCountList)
    //分组聚合后，数据根据省份进行分组
     val groupMap: Map[String, List[(String, (String, Int))]] = proToItemAndCountList.groupBy(kv=>kv._1)
    println(groupMap)

    //分组后的数据进行排序，降序
    val result: Map[String, List[(String, Int)]] = groupMap.mapValues(list => {
      val itemToCountList: List[(String, Int)] = list.map(_._2)
      itemToCountList.sortWith(
        (left, right) => {
          left._2 > right._2
        }
      )
    })
    result
    println(result)

  }
}
