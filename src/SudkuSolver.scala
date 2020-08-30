import Array._

/*
The backbone of this solver is going to be a tree, and each node
will contain a grid as well as a list of valid moves that will
lead to contradictions. This should be more effective than listing out
all valid moves.

It will only fall back on this tree implementation if it fails to find
CONCRETE DEDUCTIONS. This system will rely on deduction first and resort
to educated guessing only if necessary.

When it can't make deductions, it will guess, but when a contradiction is
reached, it will travel up the tree to the most recent guess and try something else.

Here's an exhaustive list of the features of each node:
 - pointer to previous board if applicable, otherwise root
 - flag exemplifying whether the move was a deduction or a guess
 - representation of current board
 - list of valid moves that lead to contradiction


 */

class Node(parent : Option[Grid] = None, deduction : Boolean,
           grid : Grid, invalids : List[String] = List())

class Tracker {
  var ar : Array[Boolean] = Array.fill[Boolean](10)(false)
  ar(0) = true

  def sees(value : Short): Unit = {
    ar(value) = true
  }

  // returns the number of digits's it can't see
  def count() : Int = {
    var counter = 0
    for (i <- ar) {
      if (!i) counter += 1
    }
    counter
  }

  def not_see() : Array[Short] = {
    var out = ofDim[Short](count())

    var counter = 0
    for (i <- 0 to 9) {
      if (!ar(i)) {
        out(counter) = i.toShort
        counter += 1
      }
    }

    out
  }

}




class SudkuSolver(root : Grid) {
/*
  var grid = root

  var ns = naked_search(grid)

  while (!ns.isEmpty) {
    println(ns)
    grid = grid.apply(ns.get)
    grid.prettyPrint
    ns = naked_search(grid)
  }
*/


  def get_possible_digits(grid : Grid, row_index : Int, column_index : Int) : Array[Short] = {
    val v = grid.digit_at(row_index, column_index)

    // search and record each element in it's row, column, and box
    var track : Tracker = new Tracker

    // first check it's row
    for (nc <- 0 to 8) {
      track.sees(grid.digit_at(row_index, nc))
    }

    // then it's column
    for (nr <- 0 to 8) {
      track.sees(grid.digit_at(nr, column_index))
    }

    // finally it's box
    val br = (row_index / 3) * 3
    val bc = (column_index / 3) * 3
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        track.sees(grid.digit_at(br + i, bc + j))
      }
    }

    track.not_see()
  }


  // First, here's a function that spots naked singles,
  // i.e. if a square has only 1 possible value... this
  // is a very simple form of deduction
  // we're operating under the assumption that the grid is already valid
  def naked_search(grid : Grid) : Option[String] = {
    // let's go box by box
    for (r <- 0 to 8) {
      for (c <- 0 to 8) {
        val v = grid.digit_at(r, c)
        // skip the box if it isn't empty
        if (v == grid.EMPTY) {

          var digs = get_possible_digits(grid, r, c)

          if (digs.length == 1) {
            return Some("" + digs(0) + (r+1) + (c+1))
          }

        }
      }
    }

    None
  }
}
