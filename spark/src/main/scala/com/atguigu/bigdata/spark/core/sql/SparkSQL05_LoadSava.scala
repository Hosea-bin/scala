package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders}
import org.apache.spark.{SparkConf, sql}

object SparkSQL05_LoadSava {

  def main(args: Array[String]): Unit = {

    val sparkSQL: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark =new sql.SparkSession.Builder().config(sparkSQL).getOrCreate()
    //导入隐式转换，spark是环境对象的名称,该对象必须使用val声明
    import spark.implicits._


    //SparkSQL通用的读取和保存
    //SparkSQL默认的读取数据的格式为Parquet列式存储格式
    //val frame: DataFrame = spark.read.load("input/user.json")
  val frame: DataFrame = spark.read.load("input/users.parquet")
    frame.show
    val frame1: DataFrame = spark.read.format("json").load("input/user.json")
    frame1.show

    spark.stop()
  }

}
