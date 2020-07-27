package com.atguigu.bigdata.spark.core.day06.req.controller

import com.atguigu.bigdata.spark.core.day06.req.service.{PageFlowService, WordCountService}
import com.atguigusummer.framework.core.TController

class PageFlowController extends TController{

  private val pageFlowService = new PageFlowService

  override def execute(): Unit = {
    val result = pageFlowService.analysis()

  }
}
