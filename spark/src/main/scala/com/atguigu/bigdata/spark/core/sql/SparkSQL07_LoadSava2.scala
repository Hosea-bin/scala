package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.sql.DataFrame
import org.apache.spark.{SparkConf, sql}

object SparkSQL07_LoadSava2 {

  def main(args: Array[String]): Unit = {

    val sparkSQL: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark =new sql.SparkSession.Builder().config(sparkSQL).getOrCreate()
    //导入隐式转换，spark是环境对象的名称,该对象必须使用val声明


    //SparkSQL通用的读取和保存

    //通用的保存
    spark.sql("select * from json.`input/user.json`").show()
    spark.stop()
  }

}
