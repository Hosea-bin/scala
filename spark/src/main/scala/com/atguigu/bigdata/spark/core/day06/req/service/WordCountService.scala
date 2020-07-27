package com.atguigu.bigdata.spark.core.day06.req.service

import com.atguigu.bigdata.spark.core.day06.req.dao.WordCountDao
import com.atguigusummer.framework.core.TService
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import com.atguigu.bigdata.spark.core.day06.req.application.WordCountApplication.envData
import com.atguigusummer.framework.util.EnvUtil
class WordCountService extends TService{

  /**
   * 数据分析
   */
  private val wordCountDao = new WordCountDao

  override def analysis()= {

    val fileRDD: RDD[String] = wordCountDao.readFile("input/word.txt")
    val wordRDD: RDD[String] = fileRDD.flatMap(_.split(" "))
    val mapRDD: RDD[(String, Int)] = wordRDD.map(word=>(word,1))
    val wordToSumRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_+_)
    val wordCountArray: Array[(String, Int)] = wordToSumRDD.collect()
    wordCountArray
  }
}
