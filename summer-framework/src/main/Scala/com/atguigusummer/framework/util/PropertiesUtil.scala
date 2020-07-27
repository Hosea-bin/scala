package com.atguigusummer.framework.util

import java.util.ResourceBundle

object PropertiesUtil {

  //绑定配置文件
  //ResourceBundle专门用来读取配置文件，读取时不需要增加扩展名
  //根据key来取值

  val summer: ResourceBundle = ResourceBundle.getBundle("summer")

  def getValue(key:String):String={
    summer.getString(key)
  }
}


