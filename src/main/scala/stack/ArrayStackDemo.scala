package stack

import scala.io.StdIn

/**
  * @author 杜逸文
  *         Date:2019/10/14  16:27:50
  * @Version ：1.0
  * @description:
  *
  */
object ArrayStackDemo {

  def main(args: Array[String]): Unit = {

    val stack = new ArrayStack(3)
    var key = ""
    while(true){
      println()
      println("push:入栈")
      println("list,遍历")
      println("pop,出栈")
      println("peek,查看栈顶")

      key = StdIn.readLine()
      key match {
        case "push" =>
          println("请输入一个数")
          val num = StdIn.readInt()
          stack.push(num)
        case "list" =>
          stack.list()
        case "pop" =>
          val res = stack.pop()
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception].getMessage)
          }else{
            println("取出栈顶的值="+res)
          }
        case "peek" =>
          val res = stack.pop()
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception].getMessage)
          }else{
            println("栈顶的值="+res)
          }
        case _ => println("输入错误")
      }
    }
  }

}

class ArrayStack(arrMaxSize:Int){
  val maxSize = arrMaxSize
  val arr = new Array[Int](maxSize)
  var top = -1

  //判断栈满
  def isFull():Boolean = {
    top == maxSize -1
  }
  def isEmpty():Boolean ={
    top == -1
  }

  //入栈
  def push(num:Int):Unit = {
    if(isFull()){
      println("栈满,不能push数据")
      return
    }
    top += 1
    arr(top)=num
  }

  //遍历
  def list():Unit={
    if(isEmpty()){
      println("栈空，不能遍历")
      return
    }
    //逆序遍历
    for(i <- 0 to top reverse){
      printf("arr(%d) = %d \n",i,arr(i))
    }
  }

  //出栈
  def pop():Any ={
    if(isEmpty()){
      return new Exception("栈空")
    }
    val res = arr(top)
    top -=1
    res
  }
  //查看栈顶的值，但是不弹出
  def peek():Any = {
    if(isEmpty()){
      return new Exception("栈空")
    }
    val res = arr(top)
    res
  }



}
