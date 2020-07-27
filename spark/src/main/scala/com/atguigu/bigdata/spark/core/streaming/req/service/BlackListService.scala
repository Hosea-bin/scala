package com.atguigu.bigdata.spark.core.streaming.req.service

import java.sql.{Connection, ResultSet}
import java.text.SimpleDateFormat
import java.util.Date
import com.atguigu.bigdata.spark.core.streaming.req.bean.Ad_Click_Log
import com.atguigu.bigdata.spark.core.streaming.req.dao.BlackListDao
import com.atguigusummer.framework.core.TService
import com.atguigusummer.framework.util.JDBCUtil

import org.apache.spark.streaming.dstream.DStream


import scala.collection.mutable.ListBuffer

class BlackListService extends TService{
    private val blackListDao =new BlackListDao
  /**
   * 数据分析
   */
  override def analysis() = {
    //读取Kafka数据
    val ds: DStream[String] = blackListDao.readKafka()

    //将数据转换为样例类来使用
    val logDS = ds.map(
      data => {
        val datas: Array[String] = data.split(" ")
        Ad_Click_Log(datas(0), datas(1), datas(2), datas(3), datas(4))
      }
    )
    logDS

   // val connection = JDBCUtil.getConnection()
    //周期性获取黑名单信息，判断当前用户的点击数量是否在黑名单中
    val reduceDS: DStream[((String, String, String), Int)] = logDS.transform(
      rdd => {
        //周期性执行，读取Mysql数据库，获取黑名单信息
        val connection: Connection = JDBCUtil.getConnection()
        val pstat = connection.prepareStatement(
          """
            |select userid from black_list
            |""".stripMargin
        )
        val rs: ResultSet = pstat.executeQuery() //执行查询


        //黑名单的ID集合
        val blackIds: ListBuffer[String] = ListBuffer[String]()
        while (rs.next()) {
          blackIds.append(rs.getString(1))
        }
        rs.close()
        pstat.close()
        connection.close()

        //用户若在黑名单中，则将数据进行过滤，不会进行统计
        val filterRDD = rdd.filter(
          log => {
            !blackIds.contains(log.userid)
          }
        )

        // 将正常的数据进行点击量的统计（wordCount）
        // (key,1)=>(key,sum)
        val sdf = new SimpleDateFormat("yyyy-MM-dd")
        val mapRDD = filterRDD.map(
          log => {
            val date = new Date(log.ts.toLong)
            ((sdf.format(date), log.userid, log.adid), 1)
          }
        )
        mapRDD.reduceByKey(_ + _)
      }
    )
    reduceDS

    //将统计结果超过阈值的用户信息拉到黑名单
    //数据库连接对象无法序列化
    reduceDS.foreachRDD(
      rdd =>{
        //RDD可以以分区为单位进行数据的遍历
        rdd.foreachPartition(
          datas=>{
            val conn = JDBCUtil.getConnection()
            val pstat = conn.prepareStatement(
              """
                |insert into user_ad_count(dt,userid,adid,count)
                |value(?,?,?,?)
                |on duplicate key
                |update count = count + ?
                |""".stripMargin
            )
            val pstat1 = conn.prepareStatement(
              """
                |insert into black_list (userid)
                |select userid
                |from user_ad_count
                |where dt = ? and userid = ? and adid = ? and count >= 100
                |on duplicate key
                |update userid = ?
                |""".stripMargin
            )

            datas.foreach{
              case((day,userid,adid),sum) =>{
              pstat.setString(1,day)
              pstat.setString(2,userid)
              pstat.setString(3,adid)
              pstat.setLong(4,sum)
              pstat.setLong(5,sum)
              pstat.executeUpdate()

              //获取最新的用户统计数据

              pstat1.setString(1,day)
              pstat1.setString(2,userid)
              pstat1.setString(3,adid)
              pstat1.setString(4,userid)
              pstat1.executeUpdate()

            }
            }
            pstat.close()
            pstat1.close()
            conn.close()
          }
        )

      }
    )

    //
    ds.print()
  }
  def analysis2() = {
    //读取Kafka数据
    val ds: DStream[String] = blackListDao.readKafka()

    //将数据转换为样例类来使用
    val logDS = ds.map(
      data => {
        val datas: Array[String] = data.split(" ")
        Ad_Click_Log(datas(0), datas(1), datas(2), datas(3), datas(4))
      }
    )
    logDS

    val connection = JDBCUtil.getConnection()
    //周期性获取黑名单信息，判断当前用户的点击数量是否在黑名单中
    val reduceDS: DStream[((String, String, String), Int)] = logDS.transform(
      rdd => {
        //周期性执行，读取Mysql数据库，获取黑名单信息
        val connection: Connection = JDBCUtil.getConnection()
        val pstat = connection.prepareStatement(
          """
            |select userid from black_list
            |""".stripMargin
        )
        val rs: ResultSet = pstat.executeQuery() //执行查询


        //黑名单的ID集合
        val blackIds: ListBuffer[String] = ListBuffer[String]()
        while (rs.next()) {
          blackIds.append(rs.getString(1))
        }
        rs.close()
        pstat.close()
        connection.close()

        //用户若在黑名单中，则将数据进行过滤，不会进行统计
        val filterRDD = rdd.filter(
          log => {
            !blackIds.contains(log.userid)
          }
        )

        // 将正常的数据进行点击量的统计（wordCount）
        // (key,1)=>(key,sum)
        val sdf = new SimpleDateFormat("yyyy-MM-dd")
        val mapRDD = filterRDD.map(
          log => {
            val date = new Date(log.ts.toLong)
            ((sdf.format(date), log.userid, log.adid), 1)
          }
        )
        mapRDD.reduceByKey(_ + _)
      }
    )
    reduceDS

    //将统计结果超过阈值的用户信息拉到黑名单
    //数据库连接对象无法序列化
    reduceDS.foreachRDD(
      rdd =>{
        val conn = JDBCUtil.getConnection()
        val pstat = conn.prepareStatement(
          """
            |insert into user_ad_count(dt,userid,adid,count)
            |value(?,?,?,?)
            |on duplicate key
            |update count = count + ?
            |""".stripMargin
        )
        val pstat1 = conn.prepareStatement(
          """
            |insert into black_list (userid)
            |select userid
            |from user_ad_count
            |where dt = ? and userid = ? and adid = ? and count >= 100
            |on duplicate key
            |update userid = ?
            |""".stripMargin
        )
        rdd.foreach{
          case((day,userid,adid),sum) =>{


            pstat.setString(1,day)
            pstat.setString(2,userid)
            pstat.setString(3,adid)
            pstat.setLong(4,sum)
            pstat.setLong(5,sum)
            pstat.executeUpdate()

            //获取最新的用户统计数据

            pstat1.setString(1,day)
            pstat1.setString(2,userid)
            pstat1.setString(3,adid)
            pstat1.setString(4,userid)
            pstat1.executeUpdate()

          }
        }
      }
    )

    //
    ds.print()
  }

  /**
   * 数据分析
   */
 def analysis1() = {
    //读取Kafka数据
    val ds: DStream[String] = blackListDao.readKafka()

    //将数据转换为样例类来使用
    val logDS = ds.map(
      data => {
        val datas: Array[String] = data.split(" ")
        Ad_Click_Log(datas(0), datas(1), datas(2), datas(3), datas(4))
      }
    )
    logDS

    val connection = JDBCUtil.getConnection()
    //周期性获取黑名单信息，判断当前用户的点击数量是否在黑名单中
    val reduceDS: DStream[((String, String, String), Int)] = logDS.transform(
      rdd => {
        //周期性执行，读取Mysql数据库，获取黑名单信息
        val connection: Connection = JDBCUtil.getConnection()
        val pstat = connection.prepareStatement(
          """
            |select userid from black_list
            |""".stripMargin
        )
        val rs: ResultSet = pstat.executeQuery() //执行查询


        //黑名单的ID集合
        val blackIds: ListBuffer[String] = ListBuffer[String]()
        while (rs.next()) {
          blackIds.append(rs.getString(1))
        }
        rs.close()
        pstat.close()
        connection.close()

        //用户若在黑名单中，则将数据进行过滤，不会进行统计
        val filterRDD = rdd.filter(
          log => {
            !blackIds.contains(log.userid)
          }
        )

        // 将正常的数据进行点击量的统计（wordCount）
        // (key,1)=>(key,sum)
        val sdf = new SimpleDateFormat("yyyy-MM-dd")
        val mapRDD = filterRDD.map(
          log => {
            val date = new Date(log.ts.toLong)
            ((sdf.format(date), log.userid, log.adid), 1)
          }
        )
        mapRDD.reduceByKey(_ + _)
      }
    )
    reduceDS

    //将统计结果超过阈值的用户信息拉到黑名单
    reduceDS.foreachRDD(
      rdd =>{
        rdd.foreach{
          case((day,userid,adid),sum) =>{
            //每个采集周期用户点击同一个广告的数量
            //保存状态
            //updateStateByKey=>checkpoint=>HDFS=>产生大量小文件
            //统计结果放在mysql中，更新用户点击数据量
            val conn = JDBCUtil.getConnection()
            val pstat = conn.prepareStatement(
              """
                |insert into user_ad_count(dt,userid,adid,count)
                |value(?,?,?,?)
                |on duplicate key
                |update count = count + ?
                |""".stripMargin
            )
            pstat.setString(1,day)
            pstat.setString(2,userid)
            pstat.setString(3,adid)
            pstat.setLong(4,sum)
            pstat.setLong(5,sum)
            pstat.executeUpdate()

            //获取最新的用户统计数据
            val pstat1 = conn.prepareStatement(
              """
                |insert into black_list (userid)
                |select userid
                |from user_ad_count
                |where dt = ? and userid = ? and adid = ? and count >= 100
                |on duplicate key
                |update userid = ?
                |""".stripMargin
            )
            pstat1.setString(1,day)
            pstat1.setString(2,userid)
            pstat1.setString(3,adid)
            pstat1.setString(4,userid)
            pstat1.executeUpdate()
            pstat.close()
            pstat1.close()
            conn.close()
          }
        }
      }
    )

    //
    ds.print()
  }
}
