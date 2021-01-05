package a

import a.i.{NakedSingle, PropogateDetermination}

object StandardSolver extends App{
  val g1 = new StandardSudkuGrid

  g1.grid(0)(0).determine(2)
  g1.grid(1)(0).determine(6)
  g1.grid(3)(0).determine(3)
  g1.grid(5)(0).determine(5)
  g1.grid(7)(0).determine(8)
  g1.grid(8)(0).determine(9)

  g1.grid(0)(1).determine(8)
  g1.grid(2)(1).determine(5)
  g1.grid(4)(1).determine(9)
  g1.grid(6)(1).determine(1)
  g1.grid(8)(1).determine(3)

  g1.grid(1)(2).determine(9)
  g1.grid(7)(2).determine(4)

  g1.grid(0)(3).determine(3)
  g1.grid(3)(3).determine(9)
  g1.grid(5)(3).determine(6)
  g1.grid(8)(3).determine(1)

  g1.grid(1)(4).determine(1)
  g1.grid(7)(4).determine(7)

  g1.grid(0)(5).determine(4)
  g1.grid(3)(5).determine(7)
  g1.grid(5)(5).determine(1)
  g1.grid(8)(5).determine(6)

  g1.grid(1)(6).determine(4)
  g1.grid(7)(6).determine(1)

  g1.grid(0)(7).determine(6)
  g1.grid(2)(7).determine(9)
  g1.grid(4)(7).determine(4)
  g1.grid(6)(7).determine(8)
  g1.grid(8)(7).determine(2)

  g1.grid(0)(8).determine(7)
  g1.grid(1)(8).determine(8)
  g1.grid(3)(8).determine(5)
  g1.grid(5)(8).determine(2)
  g1.grid(7)(8).determine(3)
  g1.grid(8)(8).determine(4)

  println(g1.prettyPrint())

  var det = g1.getDeterminedSquares
  while(det.length != 81){
    // first prop det on the det squares
    for(determinedSq <- det){
      val inf = new PropogateDetermination(g1, Array(Array(determinedSq.getX, determinedSq.getY)))
      if(inf.preconditionsMet())
        inf.applyInference()
    }

    // now, search for naked singles
    for(i <- 0 to 8){
      for(j <- 0 to 8){
        val inf = new NakedSingle(g1, Array(Array(i, j)))
        if(inf.preconditionsMet()) {
          println("Naked Single @(" + i + ", " + j + ")")
          inf.applyInference()
        }
      }
    }
    println(g1.toString())
    det = g1.getDeterminedSquares
  }
  println(g1.prettyPrint())

}
