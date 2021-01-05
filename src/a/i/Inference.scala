package a.i

import a.{StandardSudkuGrid, SudkuGridSquare}

abstract class Inference(ssg: StandardSudkuGrid, squaresOfInterest: Array[Array[Int]]) {

  def preconditionsMet(): Boolean
  def applyInference(): Unit

  def sq(n: Int): SudkuGridSquare={
    ssg.grid(x(n))(y(n))
  }
  def x(n: Int): Int ={
    squaresOfInterest(n)(0)
  }
  def y(n: Int): Int={
    squaresOfInterest(n)(1)
  }


}
