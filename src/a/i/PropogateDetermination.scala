package a.i

import a.{Point, StandardSudkuGrid}

class PropogateDetermination(ssg: StandardSudkuGrid, squaresOfInterest: Array[Point]) extends Inference(ssg, squaresOfInterest) {
  override def preconditionsMet(): Boolean ={
    ssg.isSquareDetermined(sq(0))
  }
  override def applyInference(): Unit ={
    val relevantSqs: List[Point] =
      ssg.getOthersInBox(x(0), y(0)) :::
      ssg.getOthersInRow(x(0), y(0)) :::
      ssg.getOthersInColumn(x(0), y(0))

    val detValue = ssg.getSquareDetermination(sq(0))

    relevantSqs.foreach((square: Point) => ssg.removeSquarePossibility(square, detValue))

  }
}
