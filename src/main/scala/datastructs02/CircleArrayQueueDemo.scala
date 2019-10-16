package datastructs02

import scala.io.StdIn

/**
  * @author 杜逸文
  *         Date:2019/10/11  20:52:52
  * @Version ：1.0
  * @description:队列
  *
  */
object CircleArrayQueueDemo {
  def main(args: Array[String]): Unit = {
    val queue = new CircleArrayQueue1(4)
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

class CircleArrayQueue1(arrMaxSize:Int){
  val maxSize = arrMaxSize //指定队列的大小
  val arr = new Array[Int](maxSize)
  var front = 0  //指向队列的第一个元素
  var rear = 0    //指定队列最后一个元素的后一个位置

  //判断队列满
  def isFull():Boolean={
    (rear+1)%maxSize == front
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
    //这里先添加 再后移
    arr(rear) = num
    //将rear后移
    rear = (rear+1)%maxSize
  }

  //3从队列中取出数据，有可能去得到数据，也可能取不到数据（异常)
  def getQueue():Any ={
    if(isEmpty()){
       return new Exception("队列空，无数据")
    }
    //因为front指向队列的第一个元素
    val res = arr(front)  //先保存到临时变量
    front = (front+1)%maxSize
    return res  //返回临时变量


  }

  //4 查看队列头元素，但是不取出
  def peek():Any ={
    if(isEmpty()){
      return new Exception("队列空无数据")
    }
    //和get的区别   少了 front += 1
    return arr(front)
  }


  //2 显示遍历队列
  /**
    * 思路
    * 1 从front 开始打印，打印多少个元素
    * 2 需要统计出该队列有多少个有效元素
    */
  def show():Unit={
    if(isEmpty()){
      println("队列空")
      return
    }

    println("队列数据情况")
    //使用取模%的方式解决
    for (i <- front until front + size()){
      printf("arr(%d)=%d \t",i%maxSize ,arr(i%maxSize))
    }
  }

  //编写一个方法，统计当前有多少个元素
  def size():Int ={
    return  (rear +maxSize - front) % maxSize
  }





}
