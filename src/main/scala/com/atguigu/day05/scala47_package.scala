package com
package atguigu
package day05{
  class User03{

  }
  package childPackage{

    object scala47_package {

    def main(args: Array[String]): Unit = {

    }
    //声明包
    //域名反写+项目名称+模块名+程序类型名称
    //包的作用：管理类，区分类，访问权限，编译类，类的源码程序和所在的包名保持一致

    // 1. scala会按照package的声明编译指定路径的class
    // 2. package关键字可以多次声明
    // 3.关键字后面增加大括号，设定作用域范围及层级关系
      //子包可以使用父包中的内容无需导入
      // 4 、包也可以当作对象。package为了和Java兼容，默认不能声明属性和函数（函数），声明类是可以的。
      // 可以将共通性的代码在包对象中声明
      val user03=new User03()
      println(user03)

      test()
  }}

}

