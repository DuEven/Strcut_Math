package datastructs02

import scala.io.StdIn

/**
  * @author 杜逸文
  *         Date:2019/10/11  20:52:52
  * @Version ：1.0
  * @description:队列
  *
  */
object ArrayQueueDemo {
  def main(args: Array[String]): Unit = {
    val queue = new ArrayQueue(3)
    //菜单演示
    var key =""
    while (true){
      println("请选择菜单")
      println("show:显示队列")
      println("add:添加数据")
      println("get:获取数据")
      println("exit:退出程序")

      key = StdIn.readLine()
      key match {
        case "show" =>queue.show()
        case "add" =>{
          println("请输入一个数")
          val num = StdIn.readInt()
          queue.addQueue(num)
        }
        case "get" =>{
          //对取回得值，进行判断
          val res = queue.getQueue()
          //如果是异常
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception].getMessage)
          }else{
            println("队列取出的值=%d",res)
          }
        }
        case "peek" =>{
          //查看头元素得值，进行判断
          val res = queue.peek()
          //如果是异常
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception].getMessage)
          }else{
            println("队列当前头元素的值=%d",res)
          }
        }
        case "exit" => System.exit(-1)
        case _ => println("出入有误")
      }
    }

  }

}

class ArrayQueue(arrMaxSize:Int){
  val maxSize = arrMaxSize //指定队列的大小
  val arr = new Array[Int](maxSize)
  var front = -1
  var rear = -1

  //判断队列满
  def isFull():Boolean={
    rear == maxSize - 1
  }
  //判断队列空
  def isEmpty():Boolean={
    front == rear
  }

  //1 向队列中添加元素 增
  def addQueue(num: Int):Unit={
    if(isFull()){
      println("队列已满无法添加")
      return
    }
    //将rear后移
    rear += 1
    arr(rear) = num
  }

  //2 显示遍历队列
  def show():Unit={
    if(isEmpty()){
      println("队列空")
      return
    }

    println("队列数据情况")
    for (i <- front+1 to rear){
      printf("arr(%d)=%d \t",i,arr(i))
    }
  }

  //3从队列中取出数据，有可能去得到数据，也可能取不到数据（异常)
  def getQueue():Any ={
    if(isEmpty()){
       return new Exception("队列空，无数据")
    }
    //将front 后移
    front += 1
    return arr(front)

  }

  //4 查看队列头元素，但是不取出
  def peek():Any ={
    if(isEmpty()){
      return new Exception("队列空无数据")
    }
    //和get的区别   少了 front += 1
    return arr(front+1)
  }





}
