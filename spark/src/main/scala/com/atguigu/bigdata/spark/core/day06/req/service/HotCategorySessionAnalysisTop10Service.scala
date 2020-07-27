package com.atguigu.bigdata.spark.core.day06.req.service

import com.atguigu.bigdata.spark.core.day06.req.bean
import com.atguigu.bigdata.spark.core.day06.req.bean.HotCategory
import com.atguigu.bigdata.spark.core.day06.req.dao.{HotCategoryAnalysisTop10Dao, HotCategorySessionAnalysisTop10Dao}
import com.atguigu.bigdata.spark.core.day06.req.helper.HotCategoryAccumulator
import com.atguigusummer.framework.core.TService
import com.atguigusummer.framework.util.EnvUtil
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD


class HotCategorySessionAnalysisTop10Service extends TService {

   private val hotCategorySessionAnalysisTop10Dao = new HotCategorySessionAnalysisTop10Dao
  /**
   * 数据分析
   */

  override def analysis(data:Any)= {
    val top10: List[HotCategory] = data.asInstanceOf[List[HotCategory]]
    val top10Ids: List[String] = top10.map(_.categoryId)
    //使用广播变量实现数据的传播
    val bcList: Broadcast[List[String]] = EnvUtil.getEnv().broadcast(top10Ids)
    //TODO 获取用户行为的数据
    val actionRDD: RDD[bean.UserVisitAction] = hotCategorySessionAnalysisTop10Dao.getUserVisitAction("input1/user_visit_action.txt")
    //对数据进行过滤
    //对用户的点击行为进行过滤
    val filterRDD: RDD[bean.UserVisitAction] = actionRDD.filter(
      action => {
        if (action.click_category_id != -1) {
          bcList.value.contains(action.click_category_id.toString)
//          var flg = false
//          top10.foreach(
//            hc => {
//              if (hc.categoryId.toLong == action.click_category_id) {
//                flg = true
//              }
//            }
//          )
//          flg
        } else {
          false
        }
      }
    )
    //将过滤后的数据进行处理 （品类_session,1）=>（品类_session,sum)
    val rdd: RDD[(String, Int)] = filterRDD.map(
      action => {
        (action.click_category_id + "_" + action.session_id, 1)
      }
    )
    val reduceRDD: RDD[(String, Int)] = rdd.reduceByKey(_+_)
    //将统计后的结果进行结构的转换 （品类_session,sum) =>(品类,(session,sum))
    val mapRDD: RDD[(String, (String, Int))] = reduceRDD.map {
      case (key, count) => {
        val ks: Array[String] = key.split("_")
        (ks(0), (ks(1), count))
      }
    }

    //将转换后的结构进行品类的分组  （品类，Iterator[(session1,sum1),(session2,sum2)]）
    val groupRDD: RDD[(String, Iterable[(String, Int)])] = mapRDD.groupByKey()
    //将分组后的数据进行排序取前10名
    val resultRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(
      iter => {
        iter.toList.sortWith(
          (left, right) => {
            left._2 > right._2
          }
        ).take(10)
      }
    )

    resultRDD.collect()

  }
}
