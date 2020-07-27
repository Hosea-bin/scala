package com.atguigu.bigdata.spark.core.streaming.req.controller

import com.atguigu.bigdata.spark.core.streaming.req.service.MockDataService
import com.atguigusummer.framework.core.TController

class MockDataController extends TController{

  private val mockDataService=new MockDataService
  override def execute(): Unit = {
    val result: Any = mockDataService.analysis()
  }
}
