/**
  * @author 杜逸文
  *         Date:2019/09/25  20:22:53
  * @Version ：1.0
  * @description:快速排序
  *
  */
object Sort01 {
  def main(args: Array[String]): Unit = {

    val arr = List(2,8,0,4,7,3,9,5,1)

    val arr1: List[Int] = MaoPSort(arr)
    println(arr1)

    val arr22 = quickSort(arr)
    val arr2 = quickSort2(arr)
    println(arr22)
    println(arr2)

    val arr3 = mergeSort(arr)((x: Int, y: Int) => x < y)
    println(arr3)
  }

  /**
    * 快速排序
    * @param list
    * @return
    */
  def quickSort(list: List[Int]): List[Int] = {
    list match {
      case Nil => Nil  //列表是空列表，返回空列表
      case base :: tail => {  //不是空列表
        val (left, right) = tail.partition(_ < base) //将列表尾部分成比首部元素小的部分和比首部元素大的部分
        //quickSort(left) ::: base :: quickSort(right)  //组合成一个新的列表——sort(比首部小的部分)+首部+sort(比首部大的部分)
        quickSort(left) ++ (base :: quickSort(right))
      }
    }
  }


  def quickSort2(list:List[Int]) :List[Int]={
    if(list.length<2)
      list
    else
      quickSort(list.filter(_<list.head)) ::: list.filter(_==list.head) ::: quickSort(list.filter(_>list.head))
      //quickSort(list.filter(_<list.head)) ++ list.filter(_==list.head) ++ quickSort(list.filter(_>list.head))
  }
  /**
    * 归并排序
    * @param left
    * @param right
    * @return
    */
  def mergeSort[T](xs:List[T])(lt: (T,T) => Boolean):List[T]={
    val n = xs.length  / 2
    if (  n == 0 ) xs
    else{
      def merge(xs: List[T],ys: List[T]):List[T] = (xs, ys) match{
        case(Nil, ys) => ys
        case(xs, Nil) => xs
        case(x:: xs1,y:: ys1)=>
          if(lt(x,y)) x::merge(xs1,ys)
          else y:: merge(xs,ys1)
      }
      val (fst,snd) = xs splitAt n
      merge(mergeSort(fst)(lt),mergeSort(snd)(lt))
    }
  }

  /**
    * 冒泡排序
    * @param list
    * @return
    */
  def MaoPSort(list: List[Int]): List[Int] = list match {
    case List() => List()
    case head :: tail => compute(head, MaoPSort(tail))
  }

  def compute(data: Int, dataSet: List[Int]): List[Int] = dataSet match {
    case List() => List(data)
    case head :: tail => if (data <= head) data :: dataSet else head :: compute(data, tail)
  }





}
