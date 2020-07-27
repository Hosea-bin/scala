package com.atguigu.bigdata.spark.core.streaming.req.controller

import com.atguigu.bigdata.spark.core.streaming.req.service.LasHourAdCountAnalysisService
import com.atguigusummer.framework.core.TController

class LasHourAdCountAnalysisController extends TController{
  private val lasHourAdCountAnalysisService = new LasHourAdCountAnalysisService

  override def execute(): Unit = {
    val result = lasHourAdCountAnalysisService.analysis()
  }
}
