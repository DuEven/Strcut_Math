package recursion

/**
  * @author 杜逸文
  *         Date:2019/10/15  11:54:20
  * @Version ：1.0
  * @description:
  *
  */
object HanoiTower {
  def main(args: Array[String]): Unit = {

    hanoiTower(100,'A','B','C')
  }

  //汉诺塔的递归方法
  /**
    * 思路
    * 如果只有一个盘 A->C
    * 如果有两个或者两个以上的盘，将看成两个部分，最下面盘  和上面的盘
    * 1 将上面的盘 A->B
    * 2 将最下面的盘 A -> C
    * 3 将B 塔的所有盘 移动到C  B-> C
    */
  def hanoiTower(nums :Int,a:Char,b:Char,c:Char):Unit={
    if(nums == 1){
      println("第1个盘从"+a + "->" + c)
    }else{
      hanoiTower(nums -1 ,a,c,b)
      println("第"+nums+"个盘从"+a+"->"+c)
      hanoiTower(nums-1,b,a,c)

    }


  }
}
