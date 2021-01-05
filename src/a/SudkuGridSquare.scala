package a

class SudkuGridSquare(x: Int, y:Int) {

  private var determined = false
  private var determinedValue: Int = -1

  private var possibleValues: List[Int] = List(1,2,3,4,5,6,7,8,9)

  def removePossibility(n: Int): Unit ={
    if(!determined)
      possibleValues = possibleValues.filter((x:Int) => x != n)
  }

  def removePossibilities(vals: List[Int]): Unit ={
    if(!determined)
      possibleValues = possibleValues.filter((x:Int) => !vals.contains(x))
  }

  def numberOfPossibilities: Int ={
    if(!determined)
      possibleValues.length
    else
      -1
  }

  def getPossibleValues: List[Int] = possibleValues

  def isPossible(n: Int): Boolean = possibleValues.contains(n)

  def isDetermined: Boolean = determined

  def determine(n: Int): Unit ={
    determined = true
    determinedValue = n
  }

  def getDeterminedValue: Int = determinedValue

  override def toString: String ={
    if(determined) "V:["+determinedValue+"]"
    else "PVS:"+possibleValues.toString().substring(4).replaceAll("\\s", "")
  }

  def matchOtherSquare(s: SudkuGridSquare): Boolean ={
    possibleValues.equals(s.getPossibleValues)
  }

  def getX: Int = x
  def getY: Int = y

}