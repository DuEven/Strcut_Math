package linkedlist

import scala.util.control.Breaks.{break, breakable}

import jdk.nashorn.internal.runtime.regexp.joni.ast.BackRefNode

/**
  * @author 杜逸文
  *         Date:2019/10/12  20:55:07
  * @Version ：1.0
  * @description:
  *
  */
object CircleLinkedListDemo {
  def main(args: Array[String]): Unit = {
    val josephu = new Josephu()

    josephu.add(5)

    //josephu.show()

    //josephu.countBoy(2,2,5)

    josephu.countBoy2(2,2,5)



  }
}

class Josephu {
  //定义一个first 初始为 null
  var first: Boy = null

  //todo 1 添加小孩形成环形链表
  def add(boyNums: Int): Unit = {
    if (boyNums < 1) {
      println("不能小于1")
      return
    }
    var curBoy: Boy = null

    for (i <- 1 to boyNums) {
      val boy = new Boy(i)
      if (i == 1) {
        first = boy
        first.next = first
        curBoy = first
      } else {
        curBoy.next = boy
        boy.next = first
        curBoy = boy
      }
    }
  }

  //todo 2 遍历
  def show(): Unit = {
    if (this.first.next == null) {
      println("没有人")
      return
    }
    var cur = first
    breakable {
      while (true) {
        if (cur.next == first) {
          break()
        }
        printf("小孩的编号=%d \n", cur.no)
        cur = cur.next
      }
    }
    //最后这个小孩输出
    printf("小孩的编号=%d \n", cur.no)

  }

  //todo 3小孩出圈
  def countBoy(startNo: Int, conutNum: Int, boyNums: Int): Unit = {
    //做参数验证
    if (first == null || startNo > boyNums || startNo < 0) {
      println("出入参数有误，不能玩")
      return
    }

    //1 创建一个辅助指针 helper
    var helper = first
    //2 让helper移动到 first的 上一个
    breakable {
      while (true) {
        if (helper.next == first) {
          break()
        }
        helper = helper.next
      }
    }
    //3 让 first 移动 k-1个位置  同时 helper 也做相应的移动
    for (i <- 1 to startNo - 1) {
      first = first.next
      helper = helper.next
    }

    breakable {
      while (true) {
        //4 让 first 和 helper在移动 m-1个位置
        for (i <- 1 to conutNum - 1) {
          first = first.next
          helper = helper.next
        }
        //5 first就指向了 要删除的小孩

        //first = first.next  helper.next = first
        //6 重复 4  5，直到圈中只有一个小孩  first == helper
        printf("小孩no = %d 出圈了 \n", first.no)
        first = first.next
        helper.next = first

        if (first == helper) {
          break()
        }
      }
    }

    printf("最后一个小孩 no = %d", first.no)

  }

  //todo 4小孩出圈
  //2.0
  def countBoy2(startNo: Int, conutNum: Int, boyNums: Int): Unit = {
    //做参数验证
    if (first == null || startNo > boyNums || startNo < 0) {
      println("出入参数有误，不能玩")
      return
    }

    //1 创建一个辅助指针 helper
    var helper = first
    //2 让helper移动到 first的 上一个
    breakable {
      while (true) {
        if (helper.next == first) {
          break()
        }
        helper = helper.next
      }
    }
    //3  helper 也做相应的移动
    for (i <- 1 to startNo - 1) {
      helper = helper.next
    }

    breakable {
      while (true) {
        //4 让 first 和 helper在移动 m-1个位置
        for (i <- 1 to conutNum - 1) {
          helper = helper.next
        }
        //5 helper.next 就指向了 要删除的小孩

        //first = first.next  helper.next = first
        //6 重复 4  5，直到圈中只有一个小孩  first == helper
        printf("小孩no = %d 出圈了 \n", helper.next.no)

        helper.next = helper.next.next

        if (helper == helper.next) {
          break()
        }
      }
    }

    printf("最后一个小孩 no = %d", helper.no)

  }
}
class Boy(bNo:Int){
  val no = bNo
  var next :Boy = null
}