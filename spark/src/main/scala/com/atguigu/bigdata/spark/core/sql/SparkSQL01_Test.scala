package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, sql}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSQL01_Test {

  def main(args: Array[String]): Unit = {
    //创建环境对象
    val sparkSQL: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark =new sql.SparkSession.Builder().config(sparkSQL).getOrCreate()
    //导入隐式转换，spark是环境对象的名称,该对象必须使用val声明
    import spark.implicits._
    //逻辑操作
    val jsonDF: DataFrame = spark.read.json("input/user.json")
    // SQL
    //将df转换为临时视图
    jsonDF.createOrReplaceTempView("user")
    spark.sql( "select* from user").show
    // DSL //查询  单引号需要隐式转换
    jsonDF.select("name","age").show
    jsonDF.select('name,'age).show

    val rdd: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List(
      (1, "zhangsan", 30),
      (2, "lisi", 20),
      (3, "wangwu", 40)
    ))
    rdd
    // RDD <=> DataFrame
    val df: DataFrame = rdd.toDF("id","name","age")
    val dfToRDD: RDD[Row] = df.rdd



    // RDD <=> DataSet
    val userRDD: RDD[User] = rdd.map {
      case (id, name, age) => {
        User(id, name, age)
      }
    }
    val userDS: Dataset[User] = userRDD.toDS()
    val dsToRDD: RDD[User] = userDS.rdd
    // DataFrame <=> DataSet
    val dfToDS: Dataset[User] = df.as[User]
    val dsToDF: DataFrame = dfToDS.toDF()

    rdd.foreach(println)
    df.show
    userDS.show

    //释放对象
    spark.stop()
  }

  case class User(id:Int,name:String,age: Int)
}
