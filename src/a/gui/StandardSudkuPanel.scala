package a.gui

import a.StandardSudkuGrid

import java.awt.event.{ActionEvent, ActionListener, KeyAdapter, KeyEvent}
import java.awt.{Color, Dimension, GridBagConstraints, GridBagLayout, Insets}
import javax.swing.{BorderFactory, JPanel}
import scala.Array.ofDim

class StandardSudkuPanel extends JPanel{
  val gridSquarePanels: Array[Array[GridSquarePanel]] = ofDim[GridSquarePanel](9,9)

  val selectionManager: ActionListener = new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      val gsp: GridSquarePanel = e.getSource.asInstanceOf[GridSquarePanel]
      if (selectedGridSquare != null)
        selectedGridSquare.active = false
      if (gsp.active)
        selectedGridSquare = gsp
      else
        selectedGridSquare = null
    }
  }
  var selectedGridSquare: GridSquarePanel = gridSquarePanels(0)(0)

  var notationActive = false

  val underlyingGrid: StandardSudkuGrid = new StandardSudkuGrid

  setLayout(new GridBagLayout)
  private[this] val gc = new GridBagConstraints
  gc.weightx = 1
  gc.weighty = 1
  gc.fill = GridBagConstraints.BOTH
  gc.anchor = GridBagConstraints.CENTER
  for(x_b <- 0 to 2; y_b <- 0 to 2){
    gc.insets = new Insets(0,0,0,0)
    val box = new JPanel(new GridBagLayout)
    box.setPreferredSize(new Dimension(3,3))
    for(i <- 0 to 2; j <- 0 to 2){
      gridSquarePanels(x_b*3+i)(y_b*3+j) = new GridSquarePanel(x_b*3+i, y_b*3+j, selectionManager)
      gc.gridx = i; gc.gridy = j
      box.add(gridSquarePanels(x_b*3+i)(y_b*3+j), gc)
    }
    gc.gridx = x_b
    gc.gridy = y_b
    gc.insets = new Insets(2,2,2,2)
    add(box, gc)
  }
  setBorder(BorderFactory.createLineBorder(Color.BLACK, 5))
  setBackground(Color.BLACK)

  override def getPreferredSize: Dimension = {
    val parentContainer = getParent
    if(parentContainer == null) return new Dimension(100,100)
    val parentPanel = parentContainer.asInstanceOf[JPanel]
    val max: Dimension = parentPanel.getSize
    val width = max.width
    val height = max.height
    val s = (if(width > height) height else width) - 20
    new Dimension(s,s)
  }

  addKeyListener(new KeyAdapter {
    override def keyTyped(e: KeyEvent): Unit = {
      if(selectedGridSquare == null) return

      val keyChar = e.getKeyChar
      if(keyChar == 'd') {
          selectedGridSquare.clearPossibleDisplay()
          selectedGridSquare.clearDetermination()
      }
      if(keyChar == 's')
        notationActive = true
      if(keyChar == 'a')
        notationActive = false
      if(Character.isDigit(keyChar)){
        val asInt = Integer.parseInt(""+keyChar)
        if(notationActive) {
          selectedGridSquare.addToPossibleDisplay(asInt)
        } else {
          selectedGridSquare.determine(asInt)
          val x = selectedGridSquare.x
          val y = selectedGridSquare.y
          underlyingGrid.grid(x)(y).determine(asInt)
        }
      }

    }

  })
  setFocusable(true)
  requestFocus()
}
