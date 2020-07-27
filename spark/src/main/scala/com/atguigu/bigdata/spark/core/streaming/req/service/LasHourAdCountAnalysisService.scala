package com.atguigu.bigdata.spark.core.streaming.req.service

import com.atguigu.bigdata.spark.core.streaming.req.bean.Ad_Click_Log
import com.atguigu.bigdata.spark.core.streaming.req.dao.LasHourAdCountAnalysisDao
import com.atguigusummer.framework.core.TService
import org.apache.spark.streaming.{Minutes, Seconds}

class LasHourAdCountAnalysisService extends TService{
    private val lasHourAdCountAnalysisDao = new LasHourAdCountAnalysisDao

  override def analysis()= {
    //读取kafka数据
    val messageDS = lasHourAdCountAnalysisDao.readKafka()
    //需求三：最近一小时广告点击量
    //将数据转换为样例类来使用
    val logDS = messageDS.map(
      data => {
        val datas: Array[String] = data.split(" ")
        Ad_Click_Log(datas(0), datas(1), datas(2), datas(3), datas(4))
      }
    )
    //将数据进行结构转换
    //（(adid,time),1）
    //10:01=>10:00 ,1
    //10:55=>10:50
    //51000 => 51000/1000 => 51 => 51/10=>5*10 =>50
    val tsToCountDS = logDS.map(
      log => {
        val ts = log.ts.toLong
        ((log.adid, ts / 10000 * 10000), 1)
      }
    )
    //将数据进行分组聚合（窗口）
    //((adid,time),1) => ((adid,time),sum)
    val tsToSumDS = tsToCountDS.reduceByKeyAndWindow(
      (x: Int, y: Int) => x + y,
      Minutes(1),
      Seconds(10)
    )

    //将数据转换后根据广告进行分组
    val adToTimeSumDS = tsToSumDS.map {
      case ((adid, time), sum) => {
        (adid, (time, sum))
      }
    }
    val groupDS = adToTimeSumDS.groupByKey()
    val resultDS = groupDS.mapValues(
      iter => {
        iter.toList.sortWith(
          (left, right) => {
            left._1 < right._1
          }
        )
      }
    )
    resultDS.print()

  }
}
