package com.atguigu.bigdata.spark.core.streaming.req.application

import com.atguigu.bigdata.spark.core.streaming.req.controller.BlackListController
import com.atguigusummer.framework.core.TApplication

object BlackListApplication extends App with TApplication{

  start("sparkStreaming") {
    val controller = new BlackListController
    controller.execute()
  }
}

