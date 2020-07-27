package com.atguigu.day04

import com.atguigusummer.framework.core.TApplication

object Scala40_Function8 {
  def main(args: Array[String]): Unit = {

    // 递归 -
    //Scala中需明确返回值类型
    //尾递归
    println(test1(5,0))
  }

  def test1(num:Int,result:Int):Int={
    if(num <1 ){
      result
    }else{
      test1(num-1,num+result)
    }
  }
}
