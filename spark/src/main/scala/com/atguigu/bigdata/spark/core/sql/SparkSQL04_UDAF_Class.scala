package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, Row}
import org.apache.spark.{SparkConf, sql}

object SparkSQL04_UDAF_Class {

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
    val ds: Dataset[User] = df.as[User]
    //创建UDAF函数
    val udaf = new MyAvgAgeUDAFClass
    //在Sql中使用聚合函数
    //该聚合函数为强类型，sql中没有类型的概念，无法使用，可以采用DSL语法访问
    ds.select(udaf.toColumn).show
    spark.stop()

  }
  case class User(id:Int,name:String,age:Long)
  case class AvgBuffer(var totalage :Long,var count:Long)
  //自定义聚合函数-强类型
  //1.继承Aggregator ,定义泛型
  //  -IN : 输入数据类型 User
  //   BUF : 缓冲区的数据类型 AvgBuffer
  //   OUT : 输出数据类型  Long
  class MyAvgAgeUDAFClass extends Aggregator[User,AvgBuffer,Long]{
    //缓冲区的初始值
    override def zero: AvgBuffer = {
      AvgBuffer(0L,0L)
    }
    //聚合数据
    override def reduce(buffer: AvgBuffer, user: User): AvgBuffer = {
      buffer.totalage=buffer.totalage+user.age
      buffer.count=buffer.count+1
      buffer
    }
    //合并缓存区
    override def merge(buffer1: AvgBuffer, buffer2: AvgBuffer): AvgBuffer = {
      buffer1.totalage=buffer1.totalage+buffer2.totalage
      buffer1.count=buffer1.count+buffer2.count
      buffer1
    }
    //计算函数结果
    override def finish(reduction: AvgBuffer): Long = {
      reduction.totalage/reduction.count
    }

    override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

    override def outputEncoder: Encoder[Long] = Encoders.scalaLong
  }

}
