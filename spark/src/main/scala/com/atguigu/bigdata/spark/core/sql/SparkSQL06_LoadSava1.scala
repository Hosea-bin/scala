package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.sql.DataFrame
import org.apache.spark.{SparkConf, sql}

object SparkSQL06_LoadSava1 {

  def main(args: Array[String]): Unit = {

    val sparkSQL: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark =new sql.SparkSession.Builder().config(sparkSQL).getOrCreate()
    //导入隐式转换，spark是环境对象的名称,该对象必须使用val声明


    //SparkSQL通用的读取和保存

    //通用的保存
    //SparkSQL默认的读取数据的格式为Parquet列式存储格式
    //val frame: DataFrame = spark.read.load("input/user.json")

    val frame1: DataFrame = spark.read.format("json").load("input/user.json")

    //spark默认保存格式为parquet
    //frame1.write.format("json").save("output")

    //路径已存在的情况下，可以使用保存模式保存数据
    frame1.write.mode("overwrite").format("json").save("output")
    spark.stop()
  }

}
