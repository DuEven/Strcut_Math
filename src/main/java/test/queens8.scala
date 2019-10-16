package test

/**
  * @author 杜逸文
  *         Date:2019/10/11  07:52:08
  * @Version ：1.0
  * @description:八皇后问题
  *
  */
object queens8 {
  def main(args: Array[String]): Unit = {

    val n = 20
    val Col = new Array[Boolean](n)
    val Bevel1 = new Array[Boolean](n*2)
    val Bevel2 = new Array[Boolean](n*2)
    def col(y: Int) = !Col(y)
    def bevel1(x: Int, y: Int) = !Bevel1(x-y+n-1)
    def bevel2(x: Int, y: Int) = !Bevel2(x+y)
    def dfs(x: Int): Int = if(x == n) 1 else Range(0, n).foldLeft(0)((a, b) => a + solve(x, b))
    def solve(x: Int, i: Int): Int = {
      if(col(i) && bevel2(x, i) && bevel1(x, i)){
        Col(i) = true; Bevel1(x-i+n-1) = true; Bevel2(x+i) = true
        val ans = dfs(x+1)
        Col(i) = false; Bevel1(x-i+n-1) = false; Bevel2(x+i) = false
        ans
      }else 0
    }
    println("The number of queue is: " + n + " " + "result: " + dfs(0))



  }

}
