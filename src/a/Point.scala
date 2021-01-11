package a

class Point(x: Int, y: Int) {
  private[this] var _x = x
  private[this] var _y = y

  def getX:Int = _x
  def getY:Int = _y

  override def equals(obj: Any): Boolean = {
    obj match {
      case p: Point =>
        p.getX == _x && p.getY == _y
      case _ =>
        false
    }
  }

}
