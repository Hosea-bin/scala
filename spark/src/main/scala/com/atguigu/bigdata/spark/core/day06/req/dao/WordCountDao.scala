package com.atguigu.bigdata.spark.core.day06.req.dao

import com.atguigusummer.framework.util.EnvUtil

class WordCountDao {
  def readFile(path:String)={
    EnvUtil.getEnv().textFile(path)
  }
}
