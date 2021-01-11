package a.i

import a.{Point, StandardSudkuGrid}

class BoxPairIdentification(ssg: StandardSudkuGrid, squaresOfInterest: Array[Point]) extends Inference(ssg, squaresOfInterest) {
  override def preconditionsMet(): Boolean = {
    if(sq(0).equals(sq(1))) return false
    if(x(0)/3 != x(1)/3 || y(0)/3 != y(1)/3) return false
    if(ssg.isSquareDetermined(sq(0)) || ssg.isSquareDetermined(sq(1))) return false

    val pos0 = ssg.getSquarePossibilities(sq(0))
    val pos1 = ssg.getSquarePossibilities(sq(1))

    val intersection = pos0.intersect(pos1)

    if(intersection.length != 2) return false

    true
  }

  override def applyInference(): Unit = {
    val relevantSqs: List[Point] = ssg.getOthersInBox(x(0), y(0))

    val values = ssg.getSquarePossibilities(sq(0))

    relevantSqs.foreach((square: Point) => ssg.removeSquarePossibilities(square, values))
  }
}
