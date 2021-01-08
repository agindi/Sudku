package a.i
import a.{Point, StandardSudkuGrid}

class NakedSingle(ssg: StandardSudkuGrid, squaresOfInterest: Array[Point]) extends Inference(ssg, squaresOfInterest) {
  // for naked single, all we need to do is check that the square of interest
  // has only one possible value
  override def preconditionsMet(): Boolean ={
    (ssg.getSquarePossibilities(sq(0)).length == 1) && !ssg.isSquareDetermined(sq(0))
  }

  override def applyInference(): Unit ={
    ssg.determineSquare(sq(0), ssg.getSquarePossibilities(sq(0)).head)
  }
}
