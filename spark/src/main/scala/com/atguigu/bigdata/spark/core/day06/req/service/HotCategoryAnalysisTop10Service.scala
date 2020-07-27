package com.atguigu.bigdata.spark.core.day06.req.service

import java.io

import com.atguigu.bigdata.spark.core.day06.req.bean.HotCategory
import com.atguigu.bigdata.spark.core.day06.req.dao.HotCategoryAnalysisTop10Dao
import com.atguigu.bigdata.spark.core.day06.req.helper.HotCategoryAccumulator
import com.atguigusummer.framework.core.TService
import com.atguigusummer.framework.util.EnvUtil
import org.apache.spark.rdd.RDD

import scala.collection.mutable
import scala.collection.mutable.ArrayOps

class HotCategoryAnalysisTop10Service extends TService {

   private val hotCategoryAnalysisTop10Dao = new HotCategoryAnalysisTop10Dao
  /**
   * 数据分析
   */
  override def analysis() = {
    //读取电商日志数据
    val actionRDD: RDD[String] = hotCategoryAnalysisTop10Dao.readFile("input1/user_visit_action.txt")

    //对品类进行点击的统计（category,clickCount）
    //使用累加器对数据进行聚合
    val acc = new HotCategoryAccumulator
    EnvUtil.getEnv().register(acc,"hotCategory")

    //将数据循环向累加器中放
    actionRDD.foreach{
      action =>{
        val datas = action.split("_")
        if(datas(6)!="-1"){
          acc.add((datas(6),"click"))
        }else if(datas(8)!="null"){
          val ids: Array[String] = datas(8).split(",")
          ids.foreach(
            id =>{
              acc.add((id,"order"))
            }
          )
        }else if(datas(10)!="null"){
          val ids: Array[String] = datas(10).split(",")
          ids.foreach(
            id =>{
              acc.add((id,"pay"))
            }
          )
        }else{
          Nil
        }
      }
    }

    //获取累加器的值
    val accValue: mutable.Map[String, HotCategory] = acc.value

    val categories: mutable.Iterable[HotCategory] = accValue.map(_._2)

    categories.toList.sortWith(
      (leftHC,rightHC)=>{
        if(leftHC.clickCount>rightHC.clickCount){
          true
        }else if(leftHC.clickCount==rightHC.clickCount){
          if(leftHC.orderCount>rightHC.orderCount){
            true
          }else if(leftHC.orderCount==rightHC.orderCount){
            leftHC.payCount>rightHC.payCount
          }
          else{
            false
          }
        }
        else{
          false
        }
      }
    ).take(10)

  }
 def analysis4() = {
    //读取电商日志数据
    val actionRDD: RDD[String] = hotCategoryAnalysisTop10Dao.readFile("input/user_visit_action.txt")

    //对品类进行点击的统计（category,clickCount）
    // line =>
    // click = HotCategory(1,0,0)
    // order = HotCategory(0,1,0)
    // pay = HotCategory(0,0,1)
    val flatMapRDD = actionRDD.flatMap(
      action => {
        val datas = action.split("_")
        if (datas(6) != "-1") {
          //点击的场合
          List((datas(6), HotCategory(datas(6),1, 0, 0)))
        } else if (datas(8) != "null") {
          //下单的场合
          val ids = datas(8).split(",")
          ids.map(id => (id,HotCategory(id, 0, 1, 0)))
        } else if (datas(10) != "null") {
          //支付的场合
          val ids = datas(10).split(",")
          ids.map(id => (id, HotCategory(id,0, 0, 1)))
        } else {
          Nil
        }
      }
    )

    val reduceRDD: RDD[(String, HotCategory)] = flatMapRDD.reduceByKey(
      (c1, c2) => {
        c1.clickCount = c1.clickCount + c2.clickCount
        c1.orderCount = c1.orderCount + c2.orderCount
        c1.payCount = c1.payCount + c2.payCount
        c1
      }
    )
    reduceRDD.collect().sortWith(
      (left,right)=>{
        val leftHC = left._2
        val rightHC = right._2
        if(leftHC.clickCount>rightHC.clickCount){
          true
        }else if(leftHC.clickCount==rightHC.clickCount){
          if(leftHC.orderCount>rightHC.orderCount){
            true
          }else if(leftHC.orderCount==rightHC.orderCount){
            leftHC.payCount>rightHC.payCount
          }
          else{
            false
          }
        }
        else{
          false
        }
      }
    ).take(10)

  }
   def analysis3() = {
    //读取电商日志数据
    val actionRDD: RDD[String] = hotCategoryAnalysisTop10Dao.readFile("input/user_visit_action.txt")

    //对品类进行点击的统计（category,clickCount）
    // line =>
    // click = (1,0,0)
    // order = (0,1,0)
    // pay = (0,0,1)
    val flatMapRDD = actionRDD.flatMap(
      action => {
        val datas = action.split("_")
        if (datas(6) != "-1") {
          //点击的场合
          List((datas(6), (1, 0, 0)))
        } else if (datas(8) != "null") {
          //下单的场合
          val ids = datas(8).split(",")
          ids.map(id => (id, (0, 1, 0)))
        } else if (datas(10) != "null") {
          //支付的场合
          val ids = datas(10).split(",")
          ids.map(id => (id, (0, 0, 1)))
        } else {
          Nil
        }
      }
    )

    val reduceRDD: RDD[(String, (Int, Int, Int))] = flatMapRDD.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )
    reduceRDD.sortBy(_._2,false).take(10)

  }

   def analysis2() = {
    //读取电商日志数据
    val actionRDD: RDD[String] = hotCategoryAnalysisTop10Dao.readFile("input/user_visit_action.txt")
    actionRDD.cache()
    //对品类进行点击的统计（category,clickCount）
    val clickRDD: RDD[(String, Int)] = actionRDD.map(
      action => {
        val datas: Array[String] = action.split("_")
        (datas(6), 1)
      }
    ).filter(_._1 != "-1")
    val categoryIdToClickCountRDD: RDD[(String, Int)] = clickRDD.reduceByKey(_+_)

    //对品类进行下单的统计（category，orderCount）
    //(品类1，品类2，品类3，10)
    //（品类1，10），（品类2，10），（品类3，10）
    val orderRDD: RDD[(String)] = actionRDD.map(
      action => {
        val datas: Array[String] = action.split("_")
        (datas(8))
      }
    ).filter(_ != "null")

    val orderToOneRDD: RDD[(String, Int)] = orderRDD.flatMap {
      ids => {
        val id: ArrayOps.ofRef[String] = ids.split(",")
        id.map(
          id => (id, 1)
        )
      }
    }
    val categoryIdToOrderCountRDD: RDD[(String, Int)] = orderToOneRDD.reduceByKey(_+_)
    //对品类进行支付的统计（category，payCount）
    val payRDD: RDD[(String)] = actionRDD.map(
      action => {
        val datas: Array[String] = action.split("_")
        (datas(10))
      }
    ).filter(_ != "null")

    val payToOneRDD: RDD[(String, Int)] = orderRDD.flatMap {
      ids => {
        val id: ArrayOps.ofRef[String] = ids.split(",")
        id.map(
          id => (id, 1)
        )
      }
    }
    val categoryIdToPayCountRDD: RDD[(String, Int)] = payToOneRDD.reduceByKey(_+_)
    //对上述统计结果转换结构
    //（品类，点击数量），（品类，下单数量），（品类，支付数量）
    //（品类，(点击数量,0,0)),(品类，(0,下单数量,0))，(品类，(0,0,支付数量))
    //(品类，（点击数量，下单数量，支付数量）)
    val newCategoryIdToClickCountRDD=categoryIdToClickCountRDD.map{
      case (id,clickCount)=>(id,(clickCount,0,0))
    }
    val newCategoryIdToOrderCountRDD=categoryIdToOrderCountRDD.map{
      case (id,orderCount)=>(id,(0,orderCount,0))
    }
    val newCategoryIdToPayCountRDD=categoryIdToPayCountRDD.map{
      case (id,payCount)=>(id,(0,0,payCount))
    }
    val countRDD: RDD[(String, (Int, Int, Int))] = newCategoryIdToClickCountRDD.union(newCategoryIdToOrderCountRDD).union(newCategoryIdToPayCountRDD)
    val reduceCountRDD: RDD[(String, (Int, Int, Int))] = countRDD.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )
    reduceCountRDD
    //将转换结构后的数据进行排序（降序）
    val sortRDD: RDD[(String, (Int, Int, Int))] = reduceCountRDD.sortBy(_._2,false)
    //将排序后的结果取前10名
    val result: Array[(String, (Int, Int, Int))] = sortRDD.take(10)
    result
  }




   def analysis1() = {
    //读取电商日志数据
    val actionRDD: RDD[String] = hotCategoryAnalysisTop10Dao.readFile("input/user_visit_action.txt")
    //对品类进行点击的统计（category,clickCount）
    val clickRDD: RDD[(String, Int)] = actionRDD.map(
      action => {
        val datas: Array[String] = action.split("_")
        (datas(6), 1)
      }
    ).filter(_._1 != "-1")
    val categoryIdToClickCountRDD: RDD[(String, Int)] = clickRDD.reduceByKey(_+_)

    //对品类进行下单的统计（category，orderCount）
    //(品类1，品类2，品类3，10)
    //（品类1，10），（品类2，10），（品类3，10）
    val orderRDD: RDD[(String)] = actionRDD.map(
      action => {
        val datas: Array[String] = action.split("_")
        (datas(8))
      }
    ).filter(_ != "null")

    val orderToOneRDD: RDD[(String, Int)] = orderRDD.flatMap {
      ids => {
        val id: ArrayOps.ofRef[String] = ids.split(",")
        id.map(
          id => (id, 1)
        )
      }
    }
    val categoryIdToOrderCountRDD: RDD[(String, Int)] = orderToOneRDD.reduceByKey(_+_)
    //对品类进行支付的统计（category，payCount）
    val payRDD: RDD[(String)] = actionRDD.map(
      action => {
        val datas: Array[String] = action.split("_")
        (datas(10))
      }
    ).filter(_ != "null")

    val payToOneRDD: RDD[(String, Int)] = orderRDD.flatMap {
      ids => {
        val id: ArrayOps.ofRef[String] = ids.split(",")
        id.map(
          id => (id, 1)
        )
      }
    }
    val categoryIdToPayCountRDD: RDD[(String, Int)] = payToOneRDD.reduceByKey(_+_)
    //对上述统计结果转换结构
    //（品类，点击数量），（品类，下单数量），（品类，支付数量）
    //(品类，（点击数量，下单数量，支付数量）)
    val joinRDD: RDD[(String, (Int, Int))] = categoryIdToClickCountRDD.join(categoryIdToOrderCountRDD)
    val joinRDD1: RDD[(String, ((Int, Int), Int))] = joinRDD.join(categoryIdToPayCountRDD)
    val mapRDD: RDD[(String, (Int, Int, Int))] = joinRDD1.mapValues {
      case ((clickCount, orderCount), payCount) => {
        (clickCount, orderCount, payCount)
      }
    }

    //将转换结构后的数据进行排序（降序）
    val sortRDD: RDD[(String, (Int, Int, Int))] = mapRDD.sortBy(_._2,false)
    //将排序后的结果取前10名
    val result: Array[(String, (Int, Int, Int))] = sortRDD.take(10)
    result
  }
}
