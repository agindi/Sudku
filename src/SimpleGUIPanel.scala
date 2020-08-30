import java.awt.event.{KeyEvent, KeyListener, MouseEvent, MouseListener}
import java.awt.{Color, Dimension, Font, Graphics, GridLayout}

import javax.swing.{BorderFactory, JLabel, JPanel}

class SimpleGUIPanel(var grid : Grid) extends JPanel {
  val backgroundColor : Color = Color.white
  val hoverColor : Color = Color.lightGray
  val selectedColor : Color = Color.PINK

  var selX : Int = -1
  var selY : Int = -1

  var lay : GridLayout = new GridLayout(3, 3)
  setLayout(lay)

  var squares : Array[Array[GridBox]] = Array.ofDim[GridBox](9, 9)

  for (i <- 0 to 8){
    for (j <- 0 to 8) {
      squares(i)(j) = new GridBox(grid.digit_at(i, j), i, j)
    }
  }

  for (br <- 0 to 2) {
    for (bc <- 0 to 2) {
      var box : JPanel = new JPanel
      box.setLayout(lay)
      box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3))

      for (i <- 0 to 2) {
        for (j <- 0 to 2) {
          box.add(squares(br*3 + i)(bc*3 + j))
        }
      }

      add(box)
    }
  }

  setPreferredSize(new Dimension(500, 500))

  def setGrid(grid : Grid): Unit = {
    this.grid = grid

    for (r <- 0 to 8) {
      for (c <- 0 to 8) {
        val dig = this.grid.digit_at(r, c)
        squares(r)(c).setDigit(dig)
      }
    }

    repaint()
  }



  class GridBox(var digit : Short = 0, val row : Int, val col : Int) extends JPanel {


    var lab : JLabel = new JLabel
    add(lab)

    var selected : Boolean = false

    if (digit != 0) {
      lab.setText(""+digit)
      lab.setFont(new Font("serif", Font.BOLD, 40))
    }

    setBackground(backgroundColor)
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 1))

    addMouseListener(new MouseListener {
      override def mouseClicked(e: MouseEvent): Unit = {
        // it it was already selected, un-select it
        if (selX == row && selY == col) {
          selX = -1
          selY = -1
          setBackground(backgroundColor)
          repaint()
        }

        // if something else is selected, uns-elect it and select this
        else if (!(selX == -1 && selY == -1)){
          squares(selX)(selY).setBackground(backgroundColor)
          selX = row
          selY = col
          setBackground(selectedColor)
          repaint()
        }

        // if there was nothing selected just select it
        else {
          selX = row
          selY = col
          setBackground(selectedColor)
          repaint()
        }

      }

      override def mousePressed(e: MouseEvent): Unit = {}

      override def mouseReleased(e: MouseEvent): Unit = {

      }

      override def mouseEntered(e: MouseEvent): Unit = {
        if (!(selX == row && selY == col)) {
          setBackground(hoverColor)
          repaint()
        }
      }

      override def mouseExited(e: MouseEvent): Unit = {
        if (!(selX == row && selY == col)) {
          setBackground(backgroundColor)
          repaint()
        }
      }
    })

    def setDigit(digit : Short): Unit ={
      this.digit = digit

      if (digit != 0) {
        lab.setText(""+digit)
        lab.setFont(new Font("serif", Font.BOLD, 40))
      }
    }

  }
}
