package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.spark.{SparkConf, sql}

object SparkSQL10_Save_MySQL {

  def main(args: Array[String]): Unit = {

    val sparkSQL: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark =new sql.SparkSession.Builder().config(sparkSQL).getOrCreate()
    //导入隐式转换，spark是环境对象的名称,该对象必须使用val声明

    //SparkSQL通用的读取和保存

    val frame: DataFrame = spark.read.format("jdbc")
      .option("url", "jdbc:mysql://hadoop14:3306/spark-sql")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user")
      .load()
    frame

    frame.write
      .format("jdbc")
      .option("url", "jdbc:mysql://hadoop14:3306/spark-sql")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user1")
      .mode(SaveMode.Append)
      .save()


    spark.stop()
  }

}
