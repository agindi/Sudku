import Array._

/**
 * This class encapsulates the behavior of a simple 9x9 Sudoku grid, namely:
 *  - options for blank grid or pre-initialized one ✓
 *  - slots for digits 1-9 in a 9x9 grid ✓
 *  - functions determining if the current configuration is valid ✓
 *  - functions determining if a move results in a valid configuration
 *  - a mutator function returning a new board from the current one making a certain move ✓
 *  - a function printing the grid as a move string ✓
 *  - a function printing the grid human-readable ✓
 *
 *  Notes:
 *  - "valid" refers to a configuration which doesn't break any core rules, i.e. repetition
 *      in a box, column, or row. It does not rule out configs which will eventually lead to
 *      a contradiction.
 *  - a "move" is a length 3 string of digits, i.e. "713" means place a 7 in row 1 column 3.
 *  - a "pre-initialized" grid is just a long string with a bunch of moves, i.e. "713814915"
*/

trait Grid {
  val EMPTY = 0

  def valid : Boolean
  def valid_after(move : String) : Boolean
  def apply(move : String) : Grid
  def digit_at(row : Int, column : Int) : Short
  def prettyPrint : Unit
}

class SimpleGrid(initial : String = "", grid : Array[Array[Short]] = ofDim[Short](9,9)) extends Grid {

  // load in initial digits
  if (!(initial == "")) {
    for (i <- 0 until initial.length() by 3) {
      val v = Integer.parseInt(initial.substring(i, i+1)).toShort
      val r = Integer.parseInt(initial.substring(i+1, i+2))
      val c = Integer.parseInt(initial.substring(i+2, i+3))

      grid(r-1)(c-1) = v

    }
  }

  def apply(move : String) : SimpleGrid = {
    var ng = ofDim[Short](9,9)
    for (i <- 0 to 8) {
      for (j <- 0 to 8) {
        ng(i)(j) = grid(i)(j)
      }
    }

    val v = Integer.parseInt(move.substring(0, 1)).toShort
    val r = Integer.parseInt(move.substring(1, 2))
    val c = Integer.parseInt(move.substring(2, 3))

    ng(r-1)(c-1) = v

    new SimpleGrid(grid = ng)
  }

  def valid : Boolean = {
    // iterate over possible values
    for (v <- 1 to 9) {

      // first check rows
      for (r <- 0 to 8) {
        var seen = false // at the start of each row, reset it
        for (c <- 0 to 8) {
          if (grid(r)(c) == v) {
            if (seen) return false
            seen = true
          }
        }
      }

      // check columns
      for (c <- 0 to 8) {
        var seen = false // at the start of each column, reset it
        for (r <- 0 to 8) {
          if (grid(r)(c) == v) {
            if (seen) return false
            seen = true
          }
        }
      }

      // check boxes
      for (br <- 0 to 2) {
        for (bc <- 0 to 2) {
          var seen = false // reset at the start of each box
          for (r <- br*3 to (br*3 + 2)) {
            for (c <- bc*3 to (bc*3 + 2)) {
              if (grid(r)(c) == v) {
                if (seen) return false
                seen = true
              }
            }
          }
        }
      }

    }

    true
  }

  def valid_after(move : String) : Boolean = {
    val ng = apply(move)
    ng.valid
  }

  def digit_at(row: Int, column: Int): Short = {
    grid(row)(column)
  }

  def prettyPrint : Unit = {
    println()
    for (c <- 1 to 9) {
      for (r <- 1 to 9) {
        print(" " + grid(c-1)(r-1) + " ")
      }
      println()
    }
  }

  override def toString: String = {
    var str = ""
    for (c <- 1 to 9) {
      for (r <- 1 to 9) {
        if (grid(r-1)(c-1) != 0) {
          str += (""+grid(r-1)(c-1)) + r + c
        }
      }
    }

    super.toString + "[" + str + "]"
  }
}
