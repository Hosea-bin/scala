package com.atguigusummer.framework.bean

class Task extends Serializable {
    var data : Int = 0
    var logic : Int => Int = null

    def compute() ={
      logic(data)
    }
}

