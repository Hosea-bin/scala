package com.atguigu.day07

object Scala75_Interface5 {
  def main(args: Array[String]): Unit = {
  new MySQL23().operDate
  }
}
//声明特质  //相同的方法执行顺序从左到右
trait Operate110{
  def operDate={
    println("操作数据")
  }
}
trait DB extends Operate110{
   override def operDate={
    println("向数据库中")
     super.operDate
  }
}
trait Log extends Operate110{
  override def operDate={
    println("向日志中")
    //super.operDate
    super[Operate110].operDate  //指定特质类型
  }
}
class MySQL23 extends DB with Log{

}