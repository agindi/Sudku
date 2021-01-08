package a.gui

import a.{Point, StandardSudkuGrid}

import java.awt.{Color, Dimension, Font, Graphics, Graphics2D, GridBagConstraints, GridBagLayout, GridLayout}
import java.awt.event.{ActionEvent, ActionListener, MouseAdapter, MouseEvent, MouseListener}
import javax.swing.border.Border
import javax.swing.{BorderFactory, JLabel, JPanel}

class GridSquarePanel(x: Int, y: Int, selector: ActionListener, overallGrid: StandardSudkuGrid) extends JPanel {

  val coordinate = new Point(x, y)
  val defaultColor: Color = Color.decode("#fcfdff")
  val mouseOverColor: Color = Color.decode("#e6eeff")
  val defaultBorder: Border = BorderFactory.createLineBorder(Color.BLACK, 1)
  val activeBorder: Border = BorderFactory.createLineBorder(Color.RED, 2)

  val determinedTextColor: Color = Color.darkGray
  val possibilityTextColor: Color = Color.lightGray

  private[this] var _active: Boolean = false
  def active: Boolean = _active
  def active_=(value: Boolean): Unit = {
    _active = value
    if(!_active) removeActiveBorder()
  }

  private[this] var _possibleDisplay: List[Int] = List()

  def determined: Boolean = overallGrid.isSquareDetermined(coordinate)
  def determinedValue: Int = overallGrid.getSquareDetermination(coordinate)

  def possibleValues: List[Int] = overallGrid.getSquarePossibilities(coordinate)

  def possibleDisplay: List[Int] = _possibleDisplay
  def addToPossibleDisplay(n: Int): Unit = {
    _possibleDisplay = n :: _possibleDisplay
    updateNumbersDisplay()
  }
  def clearPossibleDisplay(): Unit = {
    _possibleDisplay = List()
    updateNumbersDisplay()
  }
  def removeFromPossibleDisplay(values: List[Int]): Unit = {
    _possibleDisplay.filter((p: Int) => !values.contains(p))
    updateNumbersDisplay()
  }
  def intersectWithPossibleDisplay(values: List[Int]): Unit = {
    _possibleDisplay.filter((p: Int) => values.contains(p))
    updateNumbersDisplay()
  }

  def x(): Int = x
  def y(): Int = y

  addMouseListener(new MouseAdapter {
    override def mouseClicked(e: MouseEvent): Unit = {
      active = !active
      if(active) {
        drawActiveBorder()
        tellParentIsSelected()
      }

    }

    override def mouseEntered(e: MouseEvent): Unit = {
      setBackground(mouseOverColor)
    }

    override def mouseExited(e: MouseEvent): Unit = {
      setBackground(defaultColor)
    }
  })

  def tellParentIsSelected(): Unit = {
    selector.actionPerformed(new ActionEvent(this, 0, ""))
  }
  def drawActiveBorder(): Unit = setBorder(activeBorder)
  def removeActiveBorder(): Unit = setBorder(defaultBorder)

  def updateNumbersDisplay(): Unit = {
    removeAll()
    if(determined) {
      displayDeterminantion()
    } else {
      if(possibleDisplay.nonEmpty)
        displayPossibilities()
    }
    revalidate()
    repaint()
  }

  def displayDeterminantion(): Unit ={
    setLayout(new GridBagLayout)
    val s = (getWidth / 4) * 3
    val lab = new JLabel(""+determinedValue)
    lab.setFont(new Font("Monaco", Font.BOLD, s))
    lab.setForeground(determinedTextColor)
    add(lab)
  }

  def displayPossibilities(): Unit ={
    setLayout(new GridBagLayout)
    val gc = new GridBagConstraints()
    gc.weightx = 1
    gc.weighty = 1
    gc.anchor = GridBagConstraints.CENTER
    val s = getWidth / 4
    for(i: Int <- 1 to 9) {
      val lab = new JLabel("" + i)
      lab.setFont(new Font("Monaco", Font.PLAIN, s))
      lab.setForeground(Color.BLACK)
      if(!possibleDisplay.contains(i)) {
        lab.setText(" ")
      }
      gc.gridx = (i-1)%3
      gc.gridy = (i-1)/3
      add(lab, gc)
    }
  }

  setBackground(defaultColor)
  setBorder(defaultBorder)
  setPreferredSize(new Dimension(55,55))
}
