package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSQL12_Load_Hive1 {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")

    //访问外置的hive

    val spark: SparkSession =
      SparkSession.builder()
      .enableHiveSupport()
        .config(sparkConf)
        .getOrCreate()

    spark.sql("show databases").show()

    spark.stop()
  }

}
