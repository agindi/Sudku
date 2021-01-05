package a

import scala.Array.ofDim

class StandardSudkuGrid {

  var grid: Array[Array[SudkuGridSquare]] = ofDim[SudkuGridSquare](9,9)
  for(i <- 0 to 8){
    for(j <- 0 to 8){
      grid(i)(j) = new SudkuGridSquare(i, j)
    }
  }

  def getOthersInRow(x: Int, y: Int): List[SudkuGridSquare] ={
    var out: List[SudkuGridSquare] = List()
    for(x_i <- 0 to 8){
      if(x_i != x) out = grid(x_i)(y) :: out
    }
    out
  }

  def getOthersInColumn(x: Int, y: Int): List[SudkuGridSquare] ={
    var out: List[SudkuGridSquare] = List()
    for(y_i <- 0 to 8){
      if(y_i != y) out = grid(x)(y_i) :: out
    }
    out
  }

  def getOthersInBox(x: Int, y: Int): List[SudkuGridSquare] ={
    var out: List[SudkuGridSquare] = List()
    val x_b = (x / 3) * 3
    val y_b = (y / 3) * 3
    for(i <- 0 to 2){
      for(j <- 0 to 2){
        val x_i = x_b + i
        val y_i = y_b + j
        if(y_i != y && x_i != x) out = grid(x_i)(y_i) :: out
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

  def getDeterminedSquares: List[SudkuGridSquare] ={
    var out: List[SudkuGridSquare] = List()
    for(i <- 0 to 8){
      for(j <- 0 to 8){
        if(grid(i)(j).isDetermined)
          out = grid(i)(j) :: out
      }
    }
    out
  }

}
