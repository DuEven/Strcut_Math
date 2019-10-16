package datastructs01.SparseArray

import scala.collection.mutable.ArrayBuffer

/**
  * @author 杜逸文
  *         Date:2019/10/11  19:48:14
  * @Version ：1.0
  * @description:稀疏数组
  *
  */
object SparseArrayDemo {
  def main(args: Array[String]): Unit = {

    //1 先使用二维数组。映射棋盘
    val rows = 11
    val cols = 11
    val chessMap1 = Array.ofDim[Int](rows,cols)

    //初始化
    chessMap1(1)(2) = 1 //白子
    chessMap1(2)(3) = 2 //黑子

    println("原始棋盘")
    for(row <- chessMap1){
      for (item <- row){
        //printf("%d ",item)
        print(item+" ")
      }
      //换行
      println()
    }


    //2 对原始数组进行压缩
    /**
      * 思路
      * 1创建一个ArrayBuffer。可以动态的添加数据
      * 2使用node对象  表示一个数据
      */
    val sparseArray = ArrayBuffer[Node]()
    //将第一行数据放入
    var num :Int = 0
    sparseArray.append(new Node(rows,cols,num))
    //遍历chessMap1，如果发现有非0的值，就创建一个Node对象，并添加到sparseArray
    for(i<- 0 until chessMap1.length){
      for(j<- 0 until chessMap1(i).length){
        if(chessMap1(i)(j) != 0){
          sparseArray.append(new Node(i,j,chessMap1(i)(j)))
          num = num + 1
        }
      }
    }

    val numNode = new Node(rows,cols,num)
    sparseArray.update(0,numNode)

    for(i <- 0 until sparseArray.length){
      val node = sparseArray(i)
      printf("%d %d %d \n",node.row,node.col,node.value)
    }


    //3 将稀疏数组恢复成原来的数组
    /**
      * 思路：
      * 1 读取稀疏数组的第一行，创建一个二维棋盘
      * 2 遍历（稀疏数组的第二行）没读取一个node，就对应的恢复到chessMap2
      */
    val node = sparseArray(0)
    val chessMap2 = Array.ofDim[Int](node.row,node.col)
    for(i <- 1 until sparseArray.length){
      val node2 = sparseArray(i)
      chessMap2(node2.row)(node2.col) = node2.value
    }

    println("恢复后的棋盘")
    for(row <- chessMap2){
      for (item <- row){
        //printf("%d ",item)
        print(item+" ")
      }
      //换行
      println()
    }

  }

}
class Node (val row:Int,val col:Int, val value:Int)
