package com.atguigu.bigdata.spark.core.streaming.req.application

import com.atguigu.bigdata.spark.core.streaming.req.controller.{DateAreaCityAdCountAnalysisController, LasHourAdCountAnalysisController}
import com.atguigusummer.framework.core.TApplication

object LasHourAdCountAnalysisApplication extends App with TApplication{
  start("sparkStreaming") {
    val controller = new LasHourAdCountAnalysisController
    controller.execute()
  }
}
