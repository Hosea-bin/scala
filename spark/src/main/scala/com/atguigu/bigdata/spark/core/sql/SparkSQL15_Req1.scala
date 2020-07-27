package com.atguigu.bigdata.spark.core.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, MapType, StringType, StructField, StructType}


object SparkSQL15_Req1 {

  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "atguigu")
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")

    //访问外置的hive
    val spark: SparkSession =
      SparkSession.builder()
      .enableHiveSupport()
        .config(sparkConf)
        .getOrCreate()
    import spark.implicits._
    spark.sql("use hubin20200113")

    //从Hive表中获取满足条件的数据
    spark.sql(
      """
        |	select a.* ,c.area,p.product_name,c.city_name
        |			from user_visit_action a
        |			join city_info c
        |			on a.city_id = c.city_id
        |			join product_info p
        |			on a.click_product_id = p.product_id
        |			where a.click_product_id > -1
        |""".stripMargin
    ).createOrReplaceTempView("t1")
    //将数据根据区域和商品进行分组
    //in:cityName:String
    //buffer:结构：（total,map）
    //out:remark:String
    //（商品点击的总和，每个城市的总和）
    // （商品的点击总和，Map(城市，点击Sum)）
    // 城市点击sum/商品点击总和 %
      //TODO 创建自定义聚合函数
      val udaf =new CityRemarkUDAF
      //注册聚合函数
      spark.udf.register("cityRemark",udaf)

    spark.sql(
      """
        |select
        |     area,product_name,count(*) as clickCount,cityRemark(city_name)
        |	from t1 group by area,product_name
        |""".stripMargin
    ).createOrReplaceTempView("t2")

    //将统计结果降序
   spark.sql(
     """
       |select
       |	*,rank() over(partition by area order by clickCount desc) as rank
       |	from t2
       |""".stripMargin
   ).createOrReplaceTempView("t3")

    //降序取前三名
    spark.sql(
      """
        |select *
        |from t3
        |where rank <=3
        |""".stripMargin).show()

    spark.stop()
  }
  //自定义城市备注聚合函数
  class CityRemarkUDAF extends UserDefinedAggregateFunction{
    //输入的数据就是城市的名称
    override def inputSchema: StructType = {
      StructType(Array(StructField("cityName",StringType)))
    }
    //缓冲区中的数据，应该为totalcnt,Map[cityName,cnt]
    override def bufferSchema: StructType = {
      StructType(Array(
        StructField("totalcnt",LongType),
        StructField("citymap",MapType(StringType,LongType))
      ))
    }
    //返回城市备注的字符串
    override def dataType: DataType = {StringType}
    //函数稳定性
    override def deterministic: Boolean = true
    //缓冲区初始化
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer(0) = 0L
      buffer(1) = Map[String,Long]()
    }
    //更新缓冲区
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      val cityName: String = input.getString(0)
      //点击总和增加
      buffer(0)=buffer.getLong(0)+1
      //城市点击增加
      val citymap: Map[String, Long] = buffer.getAs[Map[String,Long]](1)
      val newClickCount: Long = citymap.getOrElse(cityName,0L)+1
      buffer(1)=citymap.updated(cityName,newClickCount)
  }

    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      //合并点击数量总和
      buffer1(0)=buffer1.getLong(0)+buffer2.getLong(0)
      //合并城市点击Map
      val map1: Map[String, Long] = buffer1.getAs[Map[String,Long]](1)
      val map2: Map[String, Long] = buffer2.getAs[Map[String,Long]](1)
      buffer1(1)=map1.foldLeft(map2){
        case (map,(k,v)) =>{
          map.updated(k,map.getOrElse(k,0L)+v)
        }
      }
    }
    //对缓存区进行计算并返回备注信息
    override def evaluate(buffer: Row): Any = {
      val totalcnt: Long = buffer.getLong(0)
      val citymap: collection.Map[String, Long] = buffer.getMap[String,Long](1)

      val cityToCountList: List[(String, Long)] = citymap.toList.sortWith(
        (left, right) => left._2 > right._2
      ).take(2)

      val hasRest: Boolean = citymap.size>2

        var rest = 0L
        val s = new StringBuilder
        cityToCountList.foreach{
          case (city ,cnt) =>{
            val r: Long = cnt * 100 /totalcnt
            s.append(city+" "+r+ "%,")
            rest =rest +r
          }
        }
      if(hasRest) {
        s.toString()+"其他："+(100 - rest)+"%"
      }else{
        s.toString()
      }
    }
  }

}
