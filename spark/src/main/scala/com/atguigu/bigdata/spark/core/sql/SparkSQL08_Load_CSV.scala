package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.sql.DataFrame
import org.apache.spark.{SparkConf, sql}

object SparkSQL08_Load_CSV {

  def main(args: Array[String]): Unit = {

    val sparkSQL: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark =new sql.SparkSession.Builder().config(sparkSQL).getOrCreate()
    //导入隐式转换，spark是环境对象的名称,该对象必须使用val声明
    import spark.implicits._

    //SparkSQL通用的读取和保存

    val frame: DataFrame = spark.read.format("csv")
      .option("sep", ";")
      .option("inferSchema", "true")
      .option("header", "true")
      .load("input/user.csv")
    frame.show
    spark.stop()
  }

}
