package com.atguigu.bigdata.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_File_Par {
  def main(args: Array[String]): Unit = {
    //math.min
    //math.max

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    // Spark - 从磁盘（File）中创建RDD
    //textFile 参数1 ：文件的路径   参数2 ： 最小分区数量
    //  默认值为 ：math.min(defaultParallelism,2)
    //           math.min(12,2) => 2
     val fileRDD: RDD[String] = sc.textFile("input1/w.txt")
    fileRDD.saveAsTextFile("output")

    val fileRDD1: RDD[String] = sc.textFile("input1/w.txt",1)
    fileRDD1.saveAsTextFile("output1")
    //10/4=2  2  五个
    //（0，1，2）    （0 ，2）   1
    //（3，4，5）     （2 ，4）  2
    //（6，7，8）     （4，6）  3
    //（9）          （6，8）
                      //（8，10） 4
    val fileRDD2: RDD[String] = sc.textFile("input1/w.txt",4)
    fileRDD2.saveAsTextFile("output2")

    //10/3=3  1  4个
    //（0，1，2）    （0 ，3）   1
    //（3，4，5）     （3 ，6）  2
    //（6，7，8）     （6，9）  3
    //（9）          （9）

    val fileRDD3: RDD[String] = sc.textFile("input1/w.txt",3)
    fileRDD3.saveAsTextFile("output3")
  }
}
