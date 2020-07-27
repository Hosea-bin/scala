package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Row}
import org.apache.spark.{SparkConf, sql}

object SparkSQL03_UDAF {

  def main(args: Array[String]): Unit = {

    val sparkSQL: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark =new sql.SparkSession.Builder().config(sparkSQL).getOrCreate()
    //导入隐式转换，spark是环境对象的名称,该对象必须使用val声明
    import spark.implicits._
    //逻辑操作
    //val jsonDF: DataFrame = spark.read.json("input/user.json")
    //注册UDF
    val rdd: RDD[(Int, String, Long)] = spark.sparkContext.makeRDD(List(
      (1, "zhangsan", 30L),
      (2, "lisi", 40L),
      (3, "wangwu", 50L)
    ))
    rdd
    val df: DataFrame = rdd.toDF("id","name","age")
    df.createOrReplaceTempView("user")

    //创建UDAF函数
    val udaf = new MyAvgAgeUDAF
    //注册到sparkSQL中
    spark.udf.register("avgAge",udaf)
    //在SQL中使用聚合函数
    spark.sql("select avgAge(age) from user").show()

  }

  //自定义聚合函数
  //1.继承UserDefinedAggregateFunction
  class MyAvgAgeUDAF extends UserDefinedAggregateFunction{
    //输入数据的结构信息：年龄信息
    override def inputSchema: StructType = {
      StructType(Array(StructField("age",LongType)))
    }
    //缓冲区数据结构信息：年龄的总和，人的数据
    override def bufferSchema: StructType = {
      StructType(Array(
        StructField("totalAge",LongType),
        StructField("count",LongType)
      ))
    }
    //聚合函数的返回结果类型
    override def dataType: DataType = LongType
    //函数稳定性
    override def deterministic: Boolean = true
    //函数缓冲区初始化
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer(0)=0L
      buffer(1)=0L
    }
    //更新缓冲区数据
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      buffer(0)=buffer.getLong(0)+input.getLong(0)
      buffer(1)=buffer.getLong(1)+1
    }
    //合并缓冲区
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1(0)=buffer1.getLong(0)+buffer2.getLong(0)
      buffer1(1)=buffer1.getLong(1)+buffer2.getLong(1)
    }
    //函数计算
    override def evaluate(buffer: Row): Any = {
      buffer.getLong(0)/buffer.getLong(1)
    }
  }
}
