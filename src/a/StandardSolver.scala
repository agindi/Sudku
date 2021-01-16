package a

import a.i.{BoxPairIdentification, LinePairIdentification, NakedSingle, PropagateDetermination}

object StandardSolver {

  def generateApplicableNakedSingleInferences(grid: StandardSudkuGrid): List[NakedSingle] = {
    var out: List[NakedSingle] = List()

    for(i <- 0 to 8; j <- 0 to 8){
      val inf = new NakedSingle(grid, Array(new Point(i, j)))
      if(inf.preconditionsMet()) {
        out = inf :: out
      }
    }

    out
  }

  def generateApplicablePropagateDeterminationInferences(grid: StandardSudkuGrid):
        List[PropagateDetermination] = {
    var out: List[PropagateDetermination] = List()
    val det = grid.getDeterminedSquares
    for(d <- det)
      out = new PropagateDetermination(grid, Array(new Point(d.getX, d.getY))) :: out
    out
  }

  def generateApplicableBoxPairIdentificationInferences(grid: StandardSudkuGrid): List[BoxPairIdentification] = {
    var out: List[BoxPairIdentification] = List()
    for{
      x_1 <- 0 to 8
      y_1 <- 0 to 8
      x_2 <- 0 to 8
      y_2 <- 0 to 8
    } {
      val inf = new BoxPairIdentification(grid, Array(new Point(x_1, y_1), new Point(x_2, y_2)))
      if(inf.preconditionsMet())
        out = inf :: out
    }

    println("HERE: " + out.length)

    out
  }
  def generateApplicableLinePairIdentificationInferences(grid: StandardSudkuGrid): List[LinePairIdentification] = {
    var out: List[LinePairIdentification] = List()
    for{
      x_1 <- 0 to 8
      y_1 <- 0 to 8
      x_2 <- x_1 to 8
      y_2 <- y_1 to 8
    } {
      val inf1 = new LinePairIdentification(grid, Array(new Point(x_1, y_1), new Point(x_1, y_2)))
      if(inf1.preconditionsMet())
        out = inf1 :: out

      val inf2 = new LinePairIdentification(grid, Array(new Point(x_1, y_1), new Point(x_2, y_1)))
      if(inf2.preconditionsMet())
        out = inf2 :: out
    }

    out
  }

  /*val g1 = new StandardSudkuGrid

  g1.determineSquare(new Point(0, 0), 2)
  g1.determineSquare(new Point(1, 0), 6)
  g1.determineSquare(new Point(3, 0), 3)
  g1.determineSquare(new Point(5, 0), 5)
  g1.determineSquare(new Point(7, 0), 8)
  g1.determineSquare(new Point(8, 0), 9)

  g1.determineSquare(new Point(0, 1), 8)
  g1.determineSquare(new Point(2, 1), 5)
  g1.determineSquare(new Point(4, 1), 9)
  g1.determineSquare(new Point(6, 1), 1)
  g1.determineSquare(new Point(8, 1), 3)

  g1.determineSquare(new Point(1, 2), 9)
  g1.determineSquare(new Point(7, 2), 4)

  g1.determineSquare(new Point(0, 3), 3)
  g1.determineSquare(new Point(3, 3), 9)
  g1.determineSquare(new Point(5, 3), 6)
  g1.determineSquare(new Point(8, 3), 1)

  g1.determineSquare(new Point(1, 4), 1)
  g1.determineSquare(new Point(7, 4), 7)

  g1.determineSquare(new Point(0, 5), 4)
  g1.determineSquare(new Point(3, 5), 7)
  g1.determineSquare(new Point(5, 5), 1)
  g1.determineSquare(new Point(8, 5), 6)

  g1.determineSquare(new Point(1, 6), 4)
  g1.determineSquare(new Point(7, 6), 1)

  g1.determineSquare(new Point(0, 7), 6)
  g1.determineSquare(new Point(2, 7), 9)
  g1.determineSquare(new Point(4, 7), 4)
  g1.determineSquare(new Point(6, 7), 8)
  g1.determineSquare(new Point(8, 7), 2)

  g1.determineSquare(new Point(0, 8), 7)
  g1.determineSquare(new Point(1, 8), 8)
  g1.determineSquare(new Point(3, 8), 5)
  g1.determineSquare(new Point(5, 8), 2)
  g1.determineSquare(new Point(7, 8), 3)
  g1.determineSquare(new Point(8, 8), 4)

  println(g1.prettyPrint())

  var det = g1.getDeterminedSquares
  while(det.length != 81){
    // first prop det on the det squares
    for(determinedSq <- det){
      val inf = new PropogateDetermination(g1, Array(new Point(determinedSq.getX, determinedSq.getY)))
      if(inf.preconditionsMet())
        inf.applyInference()
    }

    // now, search for naked singles
    for(i <- 0 to 8){
      for(j <- 0 to 8){
        val inf = new NakedSingle(g1, Array(new Point(i, j)))
        if(inf.preconditionsMet()) {
          println("Naked Single @(" + i + ", " + j + ")")
          inf.applyInference()
        }
      }
    }
    println(g1.prettyPrint())
    det = g1.getDeterminedSquares
  }
*/
}
