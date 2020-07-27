package com.atguigu.day03

object Scala31_Prictice4 {
  def main(args: Array[String]): Unit = {
    def split(str:String): Seq[String] ={
      var elems =List[String]()
      val quotes=str.split(" ")
      for(i<-0 to quotes.length-1){
        var word=quotes(i).substring(0,1)
      //  quotes.startsWith("s")
        if(word=="s"){
          elems=elems:+quotes(i)
        }
      }
      return elems
    }

    def filter(str:String)={
      split(str)
    }

    println(filter("hello word scala spark"))
  }
}