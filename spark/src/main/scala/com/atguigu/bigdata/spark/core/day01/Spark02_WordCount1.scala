package com.atguigu.bigdata.spark.core.day01

import java.util.Random

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, rdd}


object Spark02_WordCount1 {
  def main(args: Array[String]): Unit = {
    //使用Spark框架的API实现计算功能

    //1.准备Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("wordCount")

    //2.建立和Spark的连接
    val sc = new SparkContext(sparkConf)

    //3.实现业务操作
    //3.1读取指定文件
    val fileRDD: RDD[String] = sc.textFile("input")

    //扁平化操作，切分单词
    val wordRDD: RDD[String] = fileRDD.flatMap(_.split(" "))

    val value: RDD[(String, Int)] = wordRDD.map(
      word => {
        val random = new Random
        val prefix = random.nextInt(10)
        (prefix + "_" + word, 1)
      }
    )
    value

    //分词后的数据进行结构转换
    val mapRDD: RDD[(String, Int)] = wordRDD.map(word => (word, 1))


    //根据单词进行分组聚合
    val wordToSumRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_ + _)

    val wordCountArray: Array[(String, Int)] = wordToSumRDD.collect()
    println(wordCountArray.mkString(","))

    sc.stop()
  }


  import org.apache.spark.api.java.JavaPairRDD
  import org.apache.spark.api.java.function.PairFunction
  import java.util.Random

  val randomPrefixRdd: JavaPairRDD[String, Long] = rdd.mapToPair(new PairFunction[Nothing, String, Long]() {
    @throws[Exception]
    override def call(tuple: Nothing): Nothing = {
      val random = new Random
      val prefix = random.nextInt(10)
      new Nothing(prefix + "_" + tuple._1, tuple._2)
    }
  })

  import org.apache.spark.api.java.JavaPairRDD
  // 第二步，对打上随机前缀的key进行局部聚合。// 第二步，对打上随机前缀的key进行局部聚合。

  val localAggrRdd: JavaPairRDD[String, Long] = randomPrefixRdd.reduceByKey(new Nothing() {
    @throws[Exception]
    def call(v1: Long, v2: Long): Long = v1 + v2
  })

  import org.apache.spark.api.java.JavaPairRDD
  import org.apache.spark.api.java.function.PairFunction
  // 第三步，去除RDD中每个key的随机前缀。// 第三步，去除RDD中每个key的随机前缀。

  val removedRandomPrefixRdd: JavaPairRDD[Long, Long] = localAggrRdd.mapToPair(new PairFunction[Nothing, Long, Long]() {
    @throws[Exception]
    override def call(tuple: Nothing): Nothing = {
      val originalKey = Long.valueOf(tuple._1.split("_")(1))
      new Nothing(originalKey, tuple._2)
    }
  })

  // 第四步，对去除了随机前缀的RDD进行全局聚合。
  val globalAggrRdd: JavaPairRDD[Long, Long] = removedRandomPrefixRdd.reduceByKey(new Nothing() {
    @throws[Exception]
    def call(v1: Long, v2: Long): Long = v1 + v2
  })


  import org.apache.spark.api.java.JavaPairRDD
  import org.apache.spark.api.java.function.PairFunction
  import java.util

  val joinedRdd: JavaPairRDD[String, Nothing] = rdd2.mapToPair(new PairFunction[Nothing, String, Nothing]() {
    @throws[Exception]
    override def call(tuple: Nothing): Nothing = { // 在算子函数中，通过广播变量，获取到本地Executor中的rdd1数据。
      val rdd1Data = rdd1DataBroadcast.value
      // 可以将rdd1的数据转换为一个Map，便于后面进行join操作。
      val rdd1DataMap = new util.HashMap[Long, Nothing]
      import scala.collection.JavaConversions._
      for (data <- rdd1Data) {
        rdd1DataMap.put(data._1, data._2)
      }
      // 获取当前RDD数据的key以及value。
      val key = tuple._1
      val value = tuple._2
      // 从rdd1数据Map中，根据key获取到可以join到的数据。
      val rdd1Value = rdd1DataMap.get(key)
      new Nothing(key, new Nothing(value, rdd1Value))
    }
  })


  import org.apache.spark.api.java.JavaPairRDD
  import org.apache.spark.api.java.function.PairFunction

  val mappedSampledRDD: JavaPairRDD[Long, Long] = sampledRDD.mapToPair(new PairFunction[Nothing, Long, Long]() {
    @throws[Exception]
    override def call(tuple: Nothing) = new Nothing(tuple._1, 1L)
  })

  // 按key累加行数
  val countedSampledRDD: JavaPairRDD[Long, Long] = mappedSampledRDD.reduceByKey(new Nothing() {
    @throws[Exception]
    def call(v1: Long, v2: Long): Long = v1 + v2
  })

  // 反转key和value,变为<value,key>
  val reversedSampledRDD: JavaPairRDD[Long, Long] = countedSampledRDD.mapToPair(new PairFunction[Nothing, Long, Long]() {
    @throws[Exception]
    override def call(tuple: Nothing) = new Nothing(tuple._2, tuple._1)
  })

  // 以行数排序key，取最多行数的key
  val skewedUserid: Long = reversedSampledRDD.sortByKey(false).take(1).get(0)._2

  // 从rdd1中分拆出导致数据倾斜的key，形成独立的RDD。
  val skewedRDD: JavaPairRDD[Long, String] = rdd1.filter(new Nothing() {
    @throws[Exception]
    def call(tuple: Nothing): Boolean = tuple._1.equals(skewedUserid)
  })

  // 从rdd1中分拆出不导致数据倾斜的普通key，形成独立的RDD。
  val commonRDD: JavaPairRDD[Long, String] = rdd1.filter(new Nothing() {
    @throws[Exception]
    def call(tuple: Nothing): Boolean = !tuple._1.equals(skewedUserid)
  })

  import org.apache.spark.api.java.JavaPairRDD
  import org.apache.spark.api.java.function.PairFlatMapFunction
  import org.apache.spark.api.java.function.PairFunction
  import java.util
  import java.util.Random
  // rdd2，就是那个所有key的分布相对较为均匀的rdd。// rdd2，就是那个所有key的分布相对较为均匀的rdd。

  // 这里将rdd2中，前面获取到的key对应的数据，过滤出来，分拆成单独的rdd，并对rdd中的数据使用flatMap算子都扩容100倍。
  // 对扩容的每条数据，都打上0～100的前缀。
  val skewedRdd2: JavaPairRDD[String, Nothing] = rdd2.filter(new Nothing() {
    @throws[Exception]
    def call(tuple: Nothing): Boolean = tuple._1.equals(skewedUserid)
  }).flatMapToPair(new PairFlatMapFunction[Nothing, String, Nothing]() {
    @throws[Exception]
    override def call(tuple: Nothing): Iterable[Nothing] = {
      val random = new Random
      val list = new util.ArrayList[Nothing]
      var i = 0
      while ( {
        i < 100
      }) {
        list.add(new Nothing(i + "_" + tuple._1, tuple._2))

        {
          i += 1; i - 1
        }
      }
      list
    }
  })

  // 将rdd1中分拆出来的导致倾斜的key的独立rdd，每条数据都打上100以内的随机前缀。
  // 然后将这个rdd1中分拆出来的独立rdd，与上面rdd2中分拆出来的独立rdd，进行join。
  val joinedRDD1: JavaPairRDD[Long, Nothing] = skewedRDD.mapToPair(new PairFunction[Nothing, String, String]() {
    @throws[Exception]
    override def call(tuple: Nothing): Nothing = {
      val random = new Random
      val prefix = random.nextInt(100)
      new Nothing(prefix + "_" + tuple._1, tuple._2)
    }
  }).join(skewedUserid2infoRDD).mapToPair(new PairFunction[Nothing, Long, Nothing]() {
    @throws[Exception]
    override def call(tuple: Nothing): Nothing = {
      val key = Long.valueOf(tuple._1.split("_")(1))
      new Nothing(key, tuple._2)
    }
  })

  // 将rdd1中分拆出来的包含普通key的独立rdd，直接与rdd2进行join。
  val joinedRDD2: JavaPairRDD[Long, Nothing] = commonRDD.join(rdd2)

  // 将倾斜key join后的结果与普通key join后的结果，uinon起来。
  // 就是最终的join结果。
  val joinedRDD: JavaPairRDD[Long, Nothing] = joinedRDD1.union(joinedRDD2)
}