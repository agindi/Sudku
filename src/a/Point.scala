package a

class Point(x: Int, y: Int) {
  private[this] var _x = x
  private[this] var _y = y

  def getX:Int = _x
  def getY:Int = _y

}
