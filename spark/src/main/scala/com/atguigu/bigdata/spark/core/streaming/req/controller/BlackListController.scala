package com.atguigu.bigdata.spark.core.streaming.req.controller

import com.atguigu.bigdata.spark.core.streaming.req.service.BlackListService

import com.atguigusummer.framework.core.TController

class BlackListController extends TController{

  private val blackListService = new BlackListService

  override def execute(): Unit = {
    val result = blackListService.analysis()

  }
}
