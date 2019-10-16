package linkedlist

import scala.util.control.Breaks.{break, breakable}

/**
  * @author 杜逸文
  *         Date:2019/10/12  19:40:58
  * @Version ：1.0
  * @description:
  *
  */
object DubleLinkedListDemo {
  def main(args: Array[String]): Unit = {

    //测试 添加遍历
    val doubleLinkedList = new DoubleLinkedList()

    val node1 = new HeroNode2(1,"宋江","及时雨")
    val node2 = new HeroNode2(2,"卢俊义","玉麒麟")
    val node3 = new HeroNode2(3,"公孙胜","入云龙")
    val node4 = new HeroNode2(4,"吴用","智多星")

//    doubleLinkedList.add(node1)
//    doubleLinkedList.add(node2)
//    doubleLinkedList.add(node3)
//    doubleLinkedList.add(node4)
/*
    println("双向链表的情况")
    doubleLinkedList.showlist()

    //修改
    val node5 = new HeroNode2(4,"zhangfei","yide")
    doubleLinkedList.update(node5)
    println("修改后的情况")
    doubleLinkedList.showlist()

    //删除测试
    doubleLinkedList.delete(1)
    doubleLinkedList.delete(4)
    println("删除后情况")
    doubleLinkedList.showlist()*/

    //顺序添加
    doubleLinkedList.addByOrder(node4)
    doubleLinkedList.addByOrder(node1)
    doubleLinkedList.addByOrder(node2)
    doubleLinkedList.addByOrder(node3)
    doubleLinkedList.showlist()

  }

}

//添加  遍历 修改 删除
class DoubleLinkedList{
  //1 todo 添加 英雄到单链表

  //创建头节点，指定该链表的头部
  val head = new HeroNode2(-1,"","")

  //默认将英雄加入到链表的最后
  def add(heroNode: HeroNode2):Unit={
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
    heroNode.pre = temp
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
  def update(heroNode: HeroNode2):Unit={
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

  // todo 4 删除
  //删除结点，因为双向列表可以实现自我删除，因此我们让temp指向要删除的结点即可
  def delete(no:Int):Unit={
    if(isEmpty()){
      println("空链表")
      return
    }
    var temp = head.next
    var flag = false
    breakable{
      while (true){
        if(temp.no == no){
          flag = true
          break()
        }
        if(temp.next == null){
          break()
        }
        temp = temp.next
      }
    }

      //当退出while循环后，如果flag=true，则temp 就只想要删除结点
    if(flag){
      temp.pre.next = temp.next

      if(temp.next != null){
        temp.next.pre = temp.pre
      }else{
        temp.pre = null
      }

    }else{
      printf("要删除的 no = %d 不存在",no)
    }

  }

  //todo 5 按编号的大小插入
  def addByOrder(heroNode: HeroNode2):Unit={
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
      heroNode.next = temp.next
      //heroNode.pre = temp.next
      temp.next = heroNode

    }

  }


}

class HeroNode2(hNo:Int,hName:String, hNickname:String){
  val no = hNo
  var name = hName
  var nickname = hNickname
  var next :HeroNode2 = null //默认是null
  var pre :HeroNode2 = null //指向上一个节点
}
