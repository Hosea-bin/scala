package com.atguigu.bigdata.spark.core.day06.req.application

import com.atguigu.bigdata.spark.core.day06.req.controller.WordCountController
import com.atguigusummer.framework.core.TApplication
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object WordCountApplication extends App with TApplication{
    start("spark"){
      val controller = new WordCountController
      controller.execute()

    }
}
