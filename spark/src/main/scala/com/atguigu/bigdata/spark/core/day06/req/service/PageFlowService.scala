package com.atguigu.bigdata.spark.core.day06.req.service

import java.text.SimpleDateFormat

import com.atguigu.bigdata.spark.core.day06.req.bean
import com.atguigu.bigdata.spark.core.day06.req.dao.{PageFlowDao, WordCountDao}
import com.atguigusummer.framework.core.TService
import org.apache.spark.rdd.RDD

class PageFlowService extends TService{

  /**
   * 数据分析
   */
  private val pageFlowDao = new PageFlowDao

  override def analysis()= {

    //获取用户原始数据
    val actionRDD: RDD[bean.UserVisitAction] =
      pageFlowDao.getUserVisitAction("input1/user_visit_action.txt")
    actionRDD.cache()

    //将数据进行过滤后再进行统计
    val sessionRDD: RDD[(String, Iterable[bean.UserVisitAction])] = actionRDD.groupBy(_.session_id)

    val  pageAndTimeRDD: RDD[(String, List[(Long, Double)])] = sessionRDD.mapValues(
      iter => {
        //将分组后的数据根据时间进行排序
//        val actions: List[bean.UserVisitAction] = iter.toList.sortWith(
//          (left, right) => {
//            left.action_time < right.action_time
//          }
//        )
        val actions: List[bean.UserVisitAction] = iter.toList.sortBy(_.action_time)
        //将排序后数据进行结构的转换
        val idAndTime: List[(Long, Array[String])] = actions.map(
          action => {
            val time: Array[String] = action.action_time.split(" ")
            (action.page_id, time)
          }
        )
        idAndTime

        //格式转换 time(0).toLong * 3600 + * 60 + time(2).toLong
        val pageAndTime1: List[(Long, Long)] = idAndTime.map(
          action => {
            val day: Array[String] = action._2(0).split("-")
            val time: Array[String] = action._2(1).split(":")
            val totalTime: Long = (day(2).toLong*24*3600+time(0).toLong *3600+time(1).toLong*60+time(2).toLong)
            (action._1, totalTime)
          }
        )

        val pageAndTime2: List[((Long, Long), (Long, Long))] = pageAndTime1.zip(pageAndTime1.tail)

        actions.zip(actions.tail).map{
          case (a1,a2)=>{
            val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val t1: Long = sdf.parse(a1.action_time).getTime
            val t2: Long = sdf.parse(a2.action_time).getTime
            (a1.page_id,(t2-t1),1)

          }
        }

        pageAndTime2.map {
          case ((pageid1, time1), (pageid2, time2)) => {
            (pageid1, (time2 - time1).toDouble)
          }
        }
      }
    )
     pageAndTimeRDD

    val pageAndTimeRDD1: RDD[(Long, Iterable[Double])] = pageAndTimeRDD
      .map(_._2)
      .flatMap(list => list).groupByKey()
    pageAndTimeRDD1

    val result: RDD[(Long, Double)] = pageAndTimeRDD1.map {
      t => {
        val time: List[Double] = t._2.toList
        (t._1, (time.sum / time.size))
      }
    }
    result
    result.foreach{
      case (pageId,aveTime)=>{
        println("页面跳转【"+pageId+"】的平均停留时间为"+aveTime+"s")
      }
    }

  }

   def analysis4()= {
    //对指定页面流程进行页面单跳转换率的统计
    val flowIds = List(1,2,3,4,5,6,7)
    val okFlowIds: List[String] = flowIds.zip(flowIds.tail).map(t => (t._1+"-"+t._2))
    //获取用户原始数据
    val actionRDD: RDD[bean.UserVisitAction] =
      pageFlowDao.getUserVisitAction("input/user_visit_action.txt")
    actionRDD.cache()

    //计算分母
    //将数据进行过滤后再进行统计
    val filterRDD: RDD[bean.UserVisitAction] = actionRDD.filter(
      action => {
        flowIds.init.contains(action.page_id.toInt)
      }
    )

    val pageToOneRDD: RDD[(Long, Int)] = filterRDD.map(
      action => (action.page_id, 1)
    )

    val pageToSum: RDD[(Long, Int)] = pageToOneRDD.reduceByKey(_+_)
    val pageCountArray: Array[(Long, Int)] = pageToSum.collect()

    //计算分子
    //将数据根据用户Session进行分组
    val sessionRDD: RDD[(String, Iterable[bean.UserVisitAction])] = actionRDD.groupBy(_.session_id)

    val pageFlowRDD: RDD[(String, List[(String, Int)])] = sessionRDD.mapValues(
      iter => {
        //将分组后的数据根据时间进行排序
        val actions: List[bean.UserVisitAction] = iter.toList.sortWith(
          (left, right) => {
            left.action_time < right.action_time
          }
        )
        //将排序后数据进行结构的转换
        val pageids: List[Long] = actions.map(_.page_id)

        //将转换的结果进行格式转换
        //1，2，3，4
        //2，3，4
        //（1-2），（2-3），（3-4）
        val zipids: List[(Long, Long)] = pageids.zip(pageids.tail)
        //（（1-2），1）、（（2-3），1），（（3-4），1）
        zipids.map {
          case (pageid1, pageid2) => {
            (pageid1 + "-" + pageid2, 1)
          }
        }.filter{
          case (ids,one) =>{
            okFlowIds.contains(ids)
          }
        }
      }
    )

    //将分组后的数据进行结构转换
    val pageidSumRDD: RDD[List[(String, Int)]] = pageFlowRDD.map(_._2)

    val pageFlowRDD1: RDD[(String, Int)] = pageidSumRDD.flatMap(list=>list)

    val pageFlowToSum: RDD[(String, Int)] = pageFlowRDD1.reduceByKey(_+_)

    //计算页面单跳转换率
    pageFlowToSum.foreach{
      case (pageflow,sum)=>{
        val pageid: String = pageflow.split("-")(0)
        val value: Int = pageCountArray.toMap.getOrElse(pageid.toLong,1)
        println("页面跳转【"+pageflow+"】的转换率为："+(sum.toDouble/value))
      }
    }
  }

   def analysis1()= {

    //获取用户原始数据
    val actionRDD: RDD[bean.UserVisitAction] =
      pageFlowDao.getUserVisitAction("input/user_visit_action.txt")

    actionRDD.cache()

    //计算分母
    val pageToOneRDD: RDD[(Long, Int)] = actionRDD.map(
      action => (action.page_id, 1)
    )
    val pageToSum: RDD[(Long, Int)] = pageToOneRDD.reduceByKey(_+_)
    val pageCountArray: Array[(Long, Int)] = pageToSum.collect()

    //计算分子
    //将数据根据用户Session进行分组
    val sessionRDD: RDD[(String, Iterable[bean.UserVisitAction])] = actionRDD.groupBy(_.session_id)

    val pageFlowRDD: RDD[(String, List[(String, Int)])] = sessionRDD.mapValues(
      iter => {
        //将分组后的数据根据时间进行排序
        val actions: List[bean.UserVisitAction] = iter.toList.sortWith(
          (left, right) => {
            left.action_time < right.action_time
          }
        )
        //数据结构转换
        val pageids: List[Long] = actions.map(_.page_id)
        //将转换的结果进行格式转换
        val zipids: List[(Long, Long)] = pageids.zip(pageids.tail)
        zipids.map {
          case (pageid1, pageid2) => {
            (pageid1 + "-" + pageid2, 1)
          }
        }
      }
    )

    //将分组后的数据进行结构转换
    val pageidSumRDD: RDD[List[(String, Int)]] = pageFlowRDD.map(_._2)

    val pageFlowRDD1: RDD[(String, Int)] = pageidSumRDD.flatMap(list=>list)

    val pageFlowToSum: RDD[(String, Int)] = pageFlowRDD1.reduceByKey(_+_)

    //计算页面单跳转换率
    pageFlowToSum.foreach{
      case (pageflow,sum)=>{
        val pageid: String = pageflow.split("-")(0)
        val value: Int = pageCountArray.toMap.getOrElse(pageid.toLong,1)
        println("页面跳转【"+pageflow+"】的转换率为："+(sum.toDouble/value))
      }
    }
  }
}
