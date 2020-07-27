package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.spark.{SparkConf, sql}

object SparkSQL11_Load_Hive {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")

    //默认情况，sparkSQL支持本地Hive操作，执行前要启动Hive支持

    val spark: SparkSession = SparkSession
      .builder()
      .enableHiveSupport()
      .config(sparkConf)
      .getOrCreate()


    spark.sql("create table aa(id int)")
    spark.sql("show tables").show()
////
//    spark.sql("load data local inpath 'input/id.txt' into table aa")
//    spark.sql("select * from aa").show

//
//    spark.stop()
  }

}
