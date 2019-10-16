package linkedlist

import util.control.Breaks._
import java.util
/**
  * @author 杜逸文
  *         Date:2019/10/12  00:01:16
  * @Version ：1.0
  * @description:
  *
  */
object SingleLinkedListDemo {
  def main(args: Array[String]): Unit = {
    //测试
    val singleLinkedList = new SingleLinkedList

    val node1 = new HeroNode(1,"宋江","及时雨")
    val node2 = new HeroNode(2,"卢俊义","玉麒麟")
    val node3 = new HeroNode(3,"公孙胜","入云龙")
    val node4 = new HeroNode(4,"吴用","智多星")

//    singleLinkedList.add(node1)
//    singleLinkedList.add(node3)
//    singleLinkedList.add(node2)
//    singleLinkedList.add(node4)

    singleLinkedList.addByOrder(node1)
    singleLinkedList.addByOrder(node4)
    singleLinkedList.addByOrder(node3)
    singleLinkedList.addByOrder(node2)
    println("链表的情况")
    singleLinkedList.showlist()


    //测试节点修改
    //val node5 = new HeroNode(4,"zhangfei","yide")
    //singleLinkedList.update(node5)
    //singleLinkedList.showlist()

    //测试删除一个结点
    //singleLinkedList.delete(1)
    //singleLinkedList.delete(3)
    //singleLinkedList.delete(4)
    //singleLinkedList.delete(2)
    //singleLinkedList.showlist()

    //使用栈 完成逆序打印
    println("完成使用栈，逆序打印")
    singleLinkedList.recersePrint()


  }

}
//创建单链表的类
class SingleLinkedList{
  //创建头节点，指定该链表的头部
  val head = new HeroNode(-1,"","")

  //使用栈，从尾到头打印单链表，同时破坏链表本身的结构
  //思路
  //1 遍历 单向链表，将节点 push 到stack中
  //2 遍历stack 取出每一个结点，输出信息
  //注意：操作中没有创建新的对象，只是使用引用
  def recersePrint():Unit={
    if(isEmpty()){
      println("链表空")
      return
    }
    //创建一个栈
    val  stack =new util.Stack[HeroNode]()
    var temp = head.next
     //
    breakable{
      while (true){
        stack.push(temp)
        if(temp.next == null){
          break()
        }
        temp = temp.next
      }
    }
    //2 遍历stack 取出每一个结点，输出信息
    while(!stack.empty()){
      val heroNode  = stack.pop()
      printf("no=%d name=%s nickname=%s => \n",heroNode.no,heroNode.name,heroNode.nickname)
    }

  }


  //1 todo 添加 英雄到单链表
  //默认将英雄加入到链表的最后
  def add(heroNode: HeroNode):Unit={
    /**
      * 思路
      * 先找到链表的最后节点
      * 然后让最后的节点  .next = 新节点
      *
      * //因为head不动，因此我们使用一个辅助指针来完成定位
      */
    var temp = head

    breakable{
      while (true){
        if(temp.next == null){ //temp已到达链表的最后
          break()
        }
        temp = temp.next
      }
    }
    //当退出while时，temp指向最后
    temp.next = heroNode
  }
  //2 todo 遍历 单向链表
  /**
    * 思路
    * 让后使用temp帮助遍历
    * 判断链表是否空，空退出，不是空，直到最后节点
    */
  //记住head不能动，为什么用head.next  因为我们有效的数据不包含hade
  def isEmpty():Boolean={
    head.next == null
  }
  def showlist():Unit={
    if(isEmpty()){
      println("链表为空")
      return
    }
    var temp = head.next
    breakable{
      while (true){
        printf("no=%d name=%s nickname=%s => \n",temp.no,temp.name,temp.nickname)
        if(temp.next == null){//判断是否是最后节点
          break()
        }
        temp = temp.next
      }
    }
  }
  //3 todo 修改信息
  def update(heroNode: HeroNode):Unit={
    if(isEmpty()){
      println("链表空")
      return
    }
    //辅助指针，帮助我们定位到修改点
    var temp = head.next
    //需要定义一个变量，表示是否找到
    var flag = false

    breakable{
      while (true){
        if(temp.no == heroNode.no){
          flag =true
          break()
        }
        //判断temp 是否到最后
        if (temp.no == null){
          break()
        }
        //让 temp 后移
        temp = temp.next
      }
    }

    //判断
    if (flag){
      temp.nickname=heroNode.nickname
      temp.name = heroNode.name
    }else{//未找到
      println("要修改的未找到"+heroNode.no)
    }

  }
  // 4 todo 删除节点信息
  def delete(no:Int) :Unit={
    if(isEmpty()){
      println("空链表")
      return
    }
    //让temp指向head
    var temp = head

    var flag = false
    //遍历 让temp指向，要删除的节点的前一个节点
    breakable{
      while (true){
        if(temp.next.no == no){///找到
          flag = true
          break()
        }
        //判断 temp是否指向链表的倒数第二位
        if(temp.next.next == null){
          break()
        }
        //让temp后移，遍历
        temp = temp.next
      }
    }

    //判断flag的情况
    if(flag){
      temp.next = temp.next.next
    }else{
      printf("要删除的节点 %d 不存在",no)
    }
  }
  //todo 5 按编号的大小插入
  def addByOrder(heroNode: HeroNode):Unit={
    //让temp指向head
    var temp = head
    var flag = false //标识这个编号的结点是否存在
    breakable {
      while (true) {
        if (temp.next == null) { //判断是否是最后一个，如果是 也找到位置
          break()
        }
        if (temp.next.no == heroNode.no) { //节点已存在
          flag = true
        } else if (temp.next.no > heroNode.no) { //heorNode 应该加到temp后边
          break()
        }
        //将temp后移实现遍历
        temp = temp.next
      }
    }

      //当退出while循环后
    if (flag){
      printf("已存在no=%d 人物",heroNode.no)
    }else{
      // todo 使用temp辅助，定位要添加的节点的前一个位置，即将新的节点，添加到temp后面
      heroNode.next = temp.next
      temp.next = heroNode
    }

  }

}

class HeroNode(hNO:Int,hName:String,hNickName:String){
  val no = hNO
  var name = hName
  var nickname = hNickName
  var next :HeroNode = null //默认是null

}
