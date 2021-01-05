package a.i
import a.StandardSudkuGrid

class NakedSingle(ssg: StandardSudkuGrid, squaresOfInterest: Array[Array[Int]]) extends Inference(ssg, squaresOfInterest) {
  // for naked single, all we need to do is check that the square of interest
  // has only one possible value
  override def preconditionsMet(): Boolean ={
    sq(0).numberOfPossibilities == 1
  }

  override def applyInference(): Unit ={
    sq(0).determine(sq(0).getPossibleValues.head)
  }
}
