package com.atguigu.bigdata.spark.core.day06.req.controller

import com.atguigu.bigdata.spark.core.day06.req.bean
import com.atguigu.bigdata.spark.core.day06.req.service.{HotCategoryAnalysisTop10Service, HotCategorySessionAnalysisTop10Service}
import com.atguigusummer.framework.core.TController

class HotCategorySessionAnalysisTop10Controller extends TController{

  private val hotCategoryAnalysisTop10Service= new HotCategoryAnalysisTop10Service
  private val hotCategorySessionAnalysisTop10Service =new HotCategorySessionAnalysisTop10Service

  override def execute(): Unit = {
    val categories: List[bean.HotCategory] = hotCategoryAnalysisTop10Service.analysis()
    val result = hotCategorySessionAnalysisTop10Service.analysis(categories)
    result.foreach(println)
  }
}
