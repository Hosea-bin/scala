package com.atguiu

import com.atguigu.day01.User1

object Scala08_Rename {
  //ToDO 标识符
  def main(args: Array[String]): Unit = {
  val name : String = "zhangsan"
    val age =20
    val str = name.substring(1,7)
    //
    println(str)
    //字符串的连接
    val hello ="Hello" + name
    println(hello)

    //字符串的拼接
    printf("name=%s,age=%s",name,age)
    println()
    printf("name=%s\n", name)
    //插值字符串
    println(s"name=$name,age=$age")
//    val json="{\"username\":\"name\",\"age\":age}"
//    println(json)

    //多行字符串"""
    val json=
      s"""
        |zhangsan
        |lisi
        |{"name=$name,age=$age"}
        |""".stripMargin
    println(json)
    val teat=
      s"""
        |select
        | *
        | from user
        | where name= $name
        |""".stripMargin
    println(teat)
  }
}
