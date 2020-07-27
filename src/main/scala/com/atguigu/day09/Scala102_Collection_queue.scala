package com.atguigu.day09

import scala.collection.mutable

object Scala102_Collection_queue{

  def main(args: Array[String]): Unit = {
    val queue = new mutable.Queue[String]()
    //添加元素
    queue.enqueue("a","b","c")
    val queue1: mutable.Queue[String] = queue += "d"
    //进队和出队的方法分别为enqueue和dequeue。
    println(queue eq queue1)

    println(queue.dequeue())
    println(queue.dequeue())
    println(queue1.dequeue())
    println(queue.dequeue())
    println(queue1.dequeue())
  }
}
