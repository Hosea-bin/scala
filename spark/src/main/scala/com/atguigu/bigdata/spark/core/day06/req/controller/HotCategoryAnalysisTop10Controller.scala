package com.atguigu.bigdata.spark.core.day06.req.controller

import com.atguigu.bigdata.spark.core.day06.req.service.HotCategoryAnalysisTop10Service
import com.atguigusummer.framework.core.TController

class HotCategoryAnalysisTop10Controller extends TController{

  private val hotCategoryAnalysisTop10Service =new HotCategoryAnalysisTop10Service

  override def execute(): Unit = {
    val result = hotCategoryAnalysisTop10Service.analysis()
    result.foreach(println)
  }
}
