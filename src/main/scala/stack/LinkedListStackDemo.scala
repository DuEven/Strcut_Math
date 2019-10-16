package stack

import scala.io.StdIn
import scala.util.control.Breaks.{break, breakable}

/**
  * @author 杜逸文
  *         Date:2019/10/14  16:27:50
  * @Version ：1.0
  * @description:链表模拟栈
  *
  */
object LinkedListStackDemo {

  def main(args: Array[String]): Unit = {

    val node1 = new Node(1)
    val node2 = new Node(2)
    val node3 = new Node(3)
    val node4 = new Node(4)
    val linkedListStack = new LinkedListStack

    linkedListStack.push(node1)
    linkedListStack.push(node2)
    linkedListStack.push(node3)
    //linkedListStack.push(node4)

    linkedListStack.list()


//    var key = ""
//    while(true){
//      println()
//      println("push:入栈")
//      println("list,遍历")
//      println("pop,出栈")
//      println("peek,查看栈顶")
//
//      key = StdIn.readLine()
//      key match {
//        case "push" =>
//          println("请输入一个数")
//          val num = StdIn.readInt()
//          val node = new Node(num:Int)
//          stack.push(node)
//        case "list" =>
//          stack.list()
//        case "pop" =>
//          val res = stack.pop()
//          if(res.isInstanceOf[Exception]){
//            println(res.asInstanceOf[Exception].getMessage)
//          }else{
//            println("取出栈顶的值="+res)
//          }
//        case "peek" =>
//          val res = stack.pop()
//          if(res.isInstanceOf[Exception]){
//            println(res.asInstanceOf[Exception].getMessage)
//          }else{
//            println("栈顶的值="+res)
//          }
//        case _ => println("输入错误")
//      }
//    }
  }

}
class Node(num:Int){
  val Snode = num
  var next:Node = null
  var pre :Node= null
}
class LinkedListStack{
  var head = new Node(-1)
  var top = new Node(-1)
  val maxSize = 4
  var count = -1

  //var count = -1  //指向 最后结点的数量

  //判断栈满
  def isFull():Boolean = {
    count == maxSize -1
  }
  def isEmpty():Boolean ={
    count == 0
  }

  //入栈
  def push(node: Node):Unit = {
    if(isFull()){
      println("栈满,不能push数据")
      println("***")
      return
    }
    count match {
      case -1 =>{
        top = node
        head = node
        count += 1
        println(count)
      }
      case _ =>{
        breakable{
          while (true){
            if(isFull()){ //temp已到达链表的最后

            }
            top.next = node
            node.pre = top
          }

        }
        println(count)
        top = top.next
        count += 1
      }
    }

  }

  //遍历
  def list():Unit={
    if(isEmpty()){
      println("栈空，不能遍历")
      return
    }
    //逆序遍历
    for(i <- 0 to top.Snode reverse){
      printf("arr(%d) = %d \n",top.Snode)
    }
  }

/*
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
*/



}
