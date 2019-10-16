/**
  * @author 杜逸文
  *         Date:2019/10/15  16:13:16
  * @Version ：1.0
  * @description:
  *
  */
object test {
  def main(args: Array[String]): Unit = {

    //取前面 n 位 参数 Int类型
    println(List(1, 5, 3, 7, 9,1,4,5,6,7,8,9,0).splitAt(2))

    //按照条件分类，，true 和  flase分组，，//todo 分组(通过指定函数的返回值进行分组)
    //println(List(1, 5, 3, 7, 9, 1, 4, 5, 6, 7, 8, 9, 0).groupBy(x => x % 3 == 0))
    println(List(1, 5, 3, 7, 9, 1, 4, 5, 6, 7, 8, 9, 0).groupBy(x=>x))

    //遇到满足的 就取出来，然后停止，Boolean 类型
    println(List(1, 5, 3, 7, 9,1,4,5,6,7,8,9,0).span(_<5))

    //按照条件分组  Boolean类型
    println(List(1, 5, 3, 7, 9,1,4,5,6,7,8,9,0).partition(_<5))
  }

}
