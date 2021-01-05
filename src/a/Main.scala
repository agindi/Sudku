package a

import a.i.{NakedSingle, PropogateDetermination}

object Main extends App {

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

/*
  var inf = new NakedSingle(g1, Array(Array(0,0)))
  var inf2 = new PropogateDetermination(g1, Array(Array(0,0)))

  println(inf.preconditionsMet())
  println(inf2.preconditionsMet())

  g1.grid(0)(0).removePossibilities(List(1,2,3,4,5,6,7,8))

  println(g1.toString())

  println(inf.preconditionsMet())

  inf.applyInference()

  println(g1.toString())

  println(inf2.preconditionsMet())

  inf2.applyInference()

  println(g1.prettyPrint())
*/
}
