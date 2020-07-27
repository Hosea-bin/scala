package com.atguigu.bigdata.spark.core.day06.req.controller

import com.atguigu.bigdata.spark.core.day06.req.service.WordCountService
import com.atguigusummer.framework.core.TController

class WordCountController extends TController{

  private val wordCountService = new WordCountService

  override def execute(): Unit = {
    val wordCountArray: Array[(String, Int)] = wordCountService.analysis()
    println(wordCountArray.mkString(","))
  }
}
