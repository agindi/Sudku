package a.i

import a.{Point, StandardSudkuGrid}

class ClothedSingle(ssg: StandardSudkuGrid, squaresOfInterest: Array[Point]) extends Inference(ssg, squaresOfInterest) {

  override def preconditionsMet(): Boolean ={
    val pos = ssg.getSquarePossibilities(sq(0))

    val otherPosInBox = ssg.getOthersInBox(x(0), y(0)).map((p: Point) => ssg.getSquarePossibilities(p))
    var accum: Set[Int] = Set()

    for(o <- otherPosInBox)
      accum = accum.union(o.toSet)

    val onlyInSq = pos.filter((v: Int) => !accum.contains(v))

    onlyInSq.length == 1
  }

  override def applyInference(): Unit ={
    val pos = ssg.getSquarePossibilities(sq(0))

    val otherPosInBox = ssg.getOthersInBox(x(0), y(0)).map((p: Point) => ssg.getSquarePossibilities(p))
    var accum: Set[Int] = Set()

    for(o <- otherPosInBox)
      accum = accum.union(o.toSet)

    val onlyInSq = pos.filter((v: Int) => !accum.contains(v))
    val unique = onlyInSq.head

    ssg.determineSquare(sq(0), unique)
  }
}

