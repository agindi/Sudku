package a.gui

import java.awt.{Color, Dimension, Font, Graphics, Graphics2D, GridBagConstraints, GridBagLayout, GridLayout}
import java.awt.event.{ActionEvent, ActionListener, MouseAdapter, MouseEvent, MouseListener}
import javax.swing.border.Border
import javax.swing.{BorderFactory, JLabel, JPanel}

class GridSquarePanel(x: Int, y: Int, selector: ActionListener) extends JPanel {

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

  private[this] var _determined: Boolean = false
  private[this] var _determinedValue: Int = -1
  private[this] var _possibleValues: List[Int] = List(1,2,3,4,5,6,7,8,9)
  private[this] var _possibleDisplay: List[Int] = List()



  def determined: Boolean = _determined
  def determinedValue: Int = _determinedValue
  def determine(n: Int): Unit = {
    _determined = true
    _determinedValue = n
    updateNumbersDisplay()
  }
  def clearDetermination(): Unit = {
    _determined = false
    _determinedValue = -1
    updateNumbersDisplay()
  }

  def possibleValues: List[Int] = _possibleValues
  def possibleValues_=(value: List[Int]): Unit = {
    _possibleValues = value
  }
  def possibleDisplay: List[Int] = _possibleDisplay
  def possibleDisplay_=(value: List[Int]): Unit = {
    _possibleDisplay = value
    updateNumbersDisplay()
  }
  def addToPossibleDisplay(n: Int): Unit = {
    _possibleDisplay = n :: _possibleDisplay
    updateNumbersDisplay()
  }
  def clearPossibleDisplay(): Unit = {
    _possibleDisplay = List()
    updateNumbersDisplay()
  }

  def x: Int = x
  def y: Int = y

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
    if(_determined) {
      displayDeterminantion()
    } else {
      if(_possibleDisplay.nonEmpty)
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
  setPreferredSize(new Dimension(50,50))
}
