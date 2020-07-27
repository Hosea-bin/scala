package com.atguigu.bigdata.spark.core.day06.req.application

import com.atguigu.bigdata.spark.core.day06.req.controller.HotCategoryAnalysisTop10Controller
import com.atguigusummer.framework.core.TApplication

object HotCategoryAnalysisTop10Application extends App with TApplication{
    //热门品类前10应用程序
    start("spark"){
      val controller =new HotCategoryAnalysisTop10Controller
      controller.execute()
    }
}
