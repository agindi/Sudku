package a.i

import a.{Point, StandardSudkuGrid}

abstract class Inference(ssg: StandardSudkuGrid, squaresOfInterest: Array[Point]) {

  def preconditionsMet(): Boolean
  def applyInference(): Unit

  def x(n: Int): Int ={
    squaresOfInterest(n).getX
  }
  def y(n: Int): Int={
    squaresOfInterest(n).getY
  }
  def sq(n: Int): Point={
    squaresOfInterest(n)
  }


}
