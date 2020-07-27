package com.atguigu.day09

import scala.collection.mutable

object Scala109_Collection_Method7 {
    //合并集合
  def main(args: Array[String]): Unit = {
    val map1: mutable.Map[String, Int] = mutable.Map("a"->5,"b"->2,"c"->3)
    val map2: mutable.Map[String, Int] = mutable.Map("a"->4,"b"->1,"c"->5)

    //kv来自map1,map来自map2
    val result: mutable.Map[String, Int] = map1.foldLeft(map2)(
      (map, kv) => {
//        val k = kv._1
//        val v = kv._2
//        val newVal: Int = map.getOrElse(k, 0) + v
//        map(k) = newVal
//        map
        map.update(kv._1,map.getOrElse(kv._1,0)+kv._2)
        map
      }
    )
    result
    println(result)
  }
}
