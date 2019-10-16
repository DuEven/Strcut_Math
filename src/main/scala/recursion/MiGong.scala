package recursion

/**
  * @author 杜逸文
  *         Date:2019/10/15  10:08:56
  * @Version ：1.0
  * @description:
  *
  */
object MiGong {

  def main(args: Array[String]): Unit = {
    //创建迷宫，使用二维数组 map（8）（7）
    val map = Array.ofDim[Int](8,7)
    //给地图加墙
    //给最上面和最下面设置1
    for(i <- 0 until 7){
      map(0)(i) = 1
      map(7)(i) = 1
    }
    //给最左面和最右面设置1
    for(i <- 0 until 8){
      map(i)(0) = 1
      map(i)(6) = 1
    }
    map(3)(1)=1
    map(3)(2)=1
    map(2)(2)=1
    //map(1)(2)=1
    println("迷宫的情况")
    for(row <- map){
      for(item <- row){
        print(item + " ")
      }
      println()
    }

    setWay2(map,1,1)
    println("小球找路情况")
    for(row <- map){
      for(item <- row){
        print(item + " ")
      }
      println()
    }


  }

  //编写一个方法  完成找路
  /**
    *功能：完成递归回溯找路
    * @param map 地图
    * @param i  表示正在探索的横坐标
    * @param j  表示正在探索的纵坐标
    */
  def setWay(map:Array[Array[Int]],i:Int,j:Int):Boolean ={
    if(map(6)(5) == 2){//表示通路找到
      return true
    }else{
      //说明 当前map（i)(j) 取值 0，1，2，3
      if(map(i)(j) == 0){//说明该点还没有探测过
        //使用自己定好策略进行探测（下--右--上--左）
        //我们将map（i）（j） = 2 即假定该点可以走通，但是不一定
        map(i)(j) = 2

        if(setWay(map,i+1,j)){//向下
          return true
        }else if(setWay(map,i,j+1)){//向右
          return true
        }else if(setWay(map,i-1,j)){//向上
          return true
        }else if(setWay(map,i,j-1)){//向左
          return true
        }else{
          //说明四个方向都无法到达终点
          map(i)(j) = 3    //注意这里是 弹栈操作，不是判断语句
          //println(map(i)(j) == 3)
          return  false
        }
      }else{ //1 2 3
        return false
      }
    }
  }

  def setWay2(map: Array[Array[Int]], i: Int, j: Int): Boolean = {
    if (map(6)(5) == 2) { //如果到达(6,5),则退出第归
      printf("ok\n")
      return true
    } else {
      if (map(i)(j) == 0) { //说明这个点可以走，但是还没有走
        map(i)(j) = 2
        if (setWay2(map, i , j+1)) { //向下找
          return true
        }
        else if (setWay2(map, i+1, j )) { //向右
          return true
        } else if (setWay2(map, i - 1, j)) { //向上
          return true
        } else if (setWay2(map, i, j - 1)) { //向左
          return true
        } else { //是条死路,走不通
          map(i)(j) = 3
          return false
        }
      } else {
        return false
      }
    }

  }


}
