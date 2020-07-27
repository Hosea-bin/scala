package com.atguigu.bigdata.spark.core.day06.req.application

import com.atguigu.bigdata.spark.core.day06.req.controller.{PageFlowController, WordCountController}
import com.atguigusummer.framework.core.TApplication

object PageFlowApplication extends App with TApplication{
    start("spark"){
      val controller = new PageFlowController
      controller.execute()
    }
}
