package com.atguigu.bigdata.spark.core.streaming.req.service

import com.atguigu.bigdata.spark.core.streaming.req.dao.MockDataDao
import com.atguigusummer.framework.core.TService

class MockDataService extends TService{

  private val mockDataDao =new MockDataDao

  override def analysis() = {

//    mockDataDao.genMockData().foreach(
//      println
//    )
    val datas= mockDataDao.genMockData()
    //生成模拟数据
//    mockDataDao.genM
   // import mockDataDao._
    //向kafka中发送数据
    mockDataDao.writeToKakfa(datas)
  }
}
