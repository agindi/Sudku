package a.i

import a.{StandardSudkuGrid, SudkuGridSquare}

class PropogateDetermination(ssg: StandardSudkuGrid, squaresOfInterest: Array[Array[Int]]) extends Inference(ssg, squaresOfInterest) {
  override def preconditionsMet(): Boolean ={
    sq(0).isDetermined
  }
  override def applyInference(): Unit ={
    val relevantSqs: List[SudkuGridSquare] =
      ssg.getOthersInBox(x(0), y(0)) :::
      ssg.getOthersInRow(x(0), y(0)) :::
      ssg.getOthersInColumn(x(0), y(0))
    val detValue = sq(0).getDeterminedValue

    relevantSqs.foreach((square: SudkuGridSquare) => square.removePossibility(detValue))

  }
}
