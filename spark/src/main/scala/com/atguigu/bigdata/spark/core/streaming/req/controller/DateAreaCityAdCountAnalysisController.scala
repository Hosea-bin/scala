package com.atguigu.bigdata.spark.core.streaming.req.controller

import com.atguigu.bigdata.spark.core.streaming.req.service.DateAreaCityAdCountAnalysisService
import com.atguigusummer.framework.core.TController

class DateAreaCityAdCountAnalysisController extends TController{
  private val dateAreaCityAdCountAnalysisService = new DateAreaCityAdCountAnalysisService

  override def execute(): Unit = {
    val result = dateAreaCityAdCountAnalysisService.analysis()
  }
}
