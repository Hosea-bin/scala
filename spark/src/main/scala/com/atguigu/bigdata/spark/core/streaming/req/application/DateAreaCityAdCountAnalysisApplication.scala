package com.atguigu.bigdata.spark.core.streaming.req.application

import com.atguigu.bigdata.spark.core.streaming.req.application.BlackListApplication.start
import com.atguigu.bigdata.spark.core.streaming.req.controller.{BlackListController, DateAreaCityAdCountAnalysisController}
import com.atguigusummer.framework.core.TApplication

object DateAreaCityAdCountAnalysisApplication extends App with TApplication{
  start("sparkStreaming") {
    val controller = new DateAreaCityAdCountAnalysisController
    controller.execute()
  }
}
