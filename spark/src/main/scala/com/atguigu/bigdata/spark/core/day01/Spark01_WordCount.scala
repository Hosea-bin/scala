package com.atguigu.bigdata.spark.core.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_WordCount {
  def main(args: Array[String]): Unit = {
    //1.准备Spark环境
    //setMaster : 设定Spark环境位置
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("wordCount")

    //2.建立和Spark的连接
    //jdbc:connection
    val sc = new SparkContext(sparkConf)

    //3.实现业务操作
    //3.1读取指定目录下的数据文件，（多个）
    //参数path可以指向单一的文件也可以指向文件目录
    //RDD:更适合并行计算的数据模型
    val fileRDD: RDD[String] = sc.textFile("input1")

    //3.2将读取的内容进行扁平化操作，切分单词
    val wordRDD: RDD[String] = fileRDD.flatMap(_.split(" "))

    //3.3 将分词后的数据进行分组（单词）
    val groupRDD: RDD[(String, Iterable[String])] = wordRDD.groupBy(word=>word)

    //3,4 将分组后的数据进行聚合
    val mapRDD: RDD[(String, Int)] = groupRDD.map {
      case (word, iter) => {
        (word, iter.size)
      }
    }

    //println(mapRDD)
    mapRDD
    //3.5 将聚合后的结果采集打印到控制台
    val wordCountArray: Array[(String, Int)] = mapRDD.collect()
    println(wordCountArray.mkString(","))

    sc.stop()
  }
}
