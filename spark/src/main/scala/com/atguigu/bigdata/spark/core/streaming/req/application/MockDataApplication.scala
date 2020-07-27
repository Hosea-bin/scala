package com.atguigu.bigdata.spark.core.streaming.req.application

import com.atguigu.bigdata.spark.core.streaming.req.controller.MockDataController
import com.atguigusummer.framework.core.TApplication

object MockDataApplication extends App with TApplication{
    start("sparkStreaming"){
      val controller = new MockDataController
      controller.execute()
    }
}
