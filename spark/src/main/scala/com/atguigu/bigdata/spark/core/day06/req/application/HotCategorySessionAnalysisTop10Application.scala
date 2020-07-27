package com.atguigu.bigdata.spark.core.day06.req.application

import com.atguigu.bigdata.spark.core.day06.req.controller.{HotCategoryAnalysisTop10Controller, HotCategorySessionAnalysisTop10Controller}
import com.atguigusummer.framework.core.TApplication

object HotCategorySessionAnalysisTop10Application extends App with TApplication{
    //热门品类前10应用程序
    start("spark"){
      val controller =new HotCategorySessionAnalysisTop10Controller
      controller.execute()
    }
}
