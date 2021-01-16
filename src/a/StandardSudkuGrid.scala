package a

import scala.Array.ofDim

class StandardSudkuGrid {

  private[this] var grid: Array[Array[SudkuGridSquare]] = ofDim[SudkuGridSquare](9,9)
  for(i <- 0 to 8){
    for(j <- 0 to 8){
      grid(i)(j) = new SudkuGridSquare(i, j)
    }
  }

  def getOthersInRow(x: Int, y: Int): List[Point] ={
    var out: List[Point] = List()
    for(x_i <- 0 to 8){
      if(x_i != x) out = new Point(x_i, y) :: out
    }
    out
  }

  def getOthersInColumn(x: Int, y: Int): List[Point] ={
    var out: List[Point] = List()
    for(y_i <- 0 to 8){
      if(y_i != y) out = new Point(x, y_i) :: out
    }
    out
  }

  def getOthersInBox(x: Int, y: Int): List[Point] ={
    var out: List[Point] = List()
    val x_b = (x / 3) * 3
    val y_b = (y / 3) * 3
    for(i <- 0 to 2){
      for(j <- 0 to 2){
        val x_i = x_b + i
        val y_i = y_b + j
        if(y_i != y && x_i != x) out = new Point(x_i, y_i) :: out
      }
    }
    out
  }

  override def toString:String ={
    var out = ""
    for(i <- 0 to 8){
      for(j <- 0 to 8){
        out += grid(i)(j) + "\t"
      }
      out += "\n"
    }
    out
  }

  def prettyPrint():String ={
    var out = ""
    for(i <- 0 to 8){
      if(i % 3 == 0) out += "__________________________________\n"
      for(j <- 0 to 8){
        if(j % 3 == 0) out += "| "
        if(grid(j)(i).isDetermined)
          out += grid(j)(i).getDeterminedValue + "  "
        else
          out += "?  "
      }
      out += "| "
      out += "\n"
    }
    out += "----------------------------------\n"
    out
  }

  def getDeterminedSquares: List[Point] ={
    var out: List[Point] = List()
    for(i <- 0 to 8){
      for(j <- 0 to 8){
        if(grid(i)(j).isDetermined)
          out = new Point(i, j) :: out
      }
    }
    out
  }

  def isSquareDetermined(point : Point): Boolean = {
    getSQ(point).isDetermined
  }
  def getSquareDetermination(point : Point) : Int = {
    getSQ(point).getDeterminedValue
  }
  def getSquarePossibilities(point : Point) : List[Int] = {
    if(isSquareDetermined(point)) List()
    else getSQ(point).getPossibleValues
  }
  def removeSquarePossibility(point : Point, n : Int): Unit = {
    getSQ(point).removePossibility(n)
  }
  def removeSquarePossibilities(point : Point, values : List[Int]) : Unit = {
    getSQ(point).removePossibilities(values)
  }

  def determineSquare(point : Point, n : Int): Unit ={
    getSQ(point).determine(n)
  }

  def clearSquare(point: Point): Unit ={
    getSQ(point).clear()
  }

  def filled(): Boolean ={
    getDeterminedSquares.length == 81
  }

  private[this] def getSQ(point : Point): SudkuGridSquare = {
    grid(point.getX)(point.getY)
  }

  private[this] class SudkuGridSquare(x: Int, y:Int) {

    private var determined = false
    private var determinedValue: Int = -1

    private var possibleValues: List[Int] = List(1,2,3,4,5,6,7,8,9)

    def removePossibility(n: Int): Unit ={
      if(!determined)
        possibleValues = possibleValues.filter((x:Int) => x != n)
    }

    def removePossibilities(vals: List[Int]): Unit ={
      if(!determined)
        possibleValues = possibleValues.filter((x:Int) => !vals.contains(x))
    }

    def getPossibleValues: List[Int] = possibleValues

    def isDetermined: Boolean = determined

    def determine(n: Int): Unit ={
      determined = true
      determinedValue = n
    }

    def clear(): Unit ={
      determined = false
      determinedValue = -1
    }

    def getDeterminedValue: Int = determinedValue

    override def toString: String ={
      if(determined) "V:["+determinedValue+"]"
      else "PVS:"+possibleValues.toString().substring(4).replaceAll("\\s", "")
    }

  }

}
