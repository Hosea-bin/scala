package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row}
import org.apache.spark.{SparkConf, sql}

object SparkSQL02_Test1 {

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

    val userRDD: RDD[User] = rdd.map {
      case (id, name, age) => {
        User(id, name, age)
      }
    }
    userRDD
    val userDS: Dataset[User] = userRDD.toDS()
    val newDS: Dataset[User] = userDS.map {
      user => {
        User(user.id, "name:" + user.name, user.age)
      }
    }
    newDS.show

    //自定义函数在Sql中完成数据的转换
    spark.udf.register("addName",(x:String)=> "Name:"+x)
    spark.udf.register("changeAge",(x:Int)=>18)
    spark.sql("select addName(name),changeAge(age) from user").show

    spark.stop()
  }

  case class User(id:Int,name:String,age: Long)
}
