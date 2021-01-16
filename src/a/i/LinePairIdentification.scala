package a.i

import a.{Point, StandardSudkuGrid}

class LinePairIdentification(ssg: StandardSudkuGrid, squaresOfInterest: Array[Point]) extends Inference(ssg, squaresOfInterest) {
  override def preconditionsMet(): Boolean = {
    if(sq(0).equals(sq(1))) return false
    if(x(0) != x(1) && y(0) != y(1)) return false
    if(ssg.isSquareDetermined(sq(0)) || ssg.isSquareDetermined(sq(1))) return false

    val pos0 = ssg.getSquarePossibilities(sq(0))
    val pos1 = ssg.getSquarePossibilities(sq(1))

    if(pos0.length != 2 || pos1.length != 2) return false

    val potentialPair = pos0.intersect(pos1)

    if(potentialPair.length != 2) return false

    true
  }

  override def applyInference(): Unit = {
    val pair = ssg.getSquarePossibilities(sq(0))

    if(x(0) == x(1)){
      for(square <- ssg.getOthersInColumn(x(0), y(0))){
        if(!square.equals(sq(0)) && !square.equals(sq(1))){
          ssg.removeSquarePossibilities(square, pair)
        }
      }

    } else { // y(0) == y(1)
      for(square <- ssg.getOthersInRow(x(0), y(0))){
        if(!square.equals(sq(0)) && !square.equals(sq(1))){
          ssg.removeSquarePossibilities(square, pair)
        }
      }

    }
  }
}
