package a.gui

import a.Point
import a.StandardSolver.{det, g1}
import a.i.{NakedSingle, PropogateDetermination}

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{GridBagConstraints, GridBagLayout}
import javax.swing.border.EtchedBorder
import javax.swing.{BorderFactory, JButton, JFrame, JPanel}

class StandardGUI extends JFrame{

  val cont: JPanel = new JPanel(new GridBagLayout)
  val contGC: GridBagConstraints = new GridBagConstraints

  val sudukuPanel: StandardSudkuPanel = new StandardSudkuPanel

  val controlPanel = new JPanel
  val startButton: JButton = new JButton("Start")

  startButton.addActionListener(new ActionListener {
    override def actionPerformed(e: ActionEvent): Unit = {
      val ssg = sudukuPanel.getUnderlyingGrid
      // first prop det on the det squares
      for(determinedSq <- ssg.getDeterminedSquares){
        val inf = new PropogateDetermination(ssg, Array(new Point(determinedSq.getX, determinedSq.getY)))
        if(inf.preconditionsMet())
          inf.applyInference()
      }

      // now, search for naked singles
      for(i <- 0 to 8){
        for(j <- 0 to 8){
          val inf = new NakedSingle(ssg, Array(new Point(i, j)))
          if(inf.preconditionsMet()) {
            println("Naked Single @(" + i + ", " + j + ")")
            inf.applyInference()
          }
        }
      }
      sudukuPanel.updateDisplay()
    }
  })

  controlPanel.add(startButton)
  controlPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED))

  contGC.weightx = 1.0
  contGC.weighty = 1.0
  contGC.fill = GridBagConstraints.NONE
  contGC.anchor = GridBagConstraints.CENTER
  contGC.gridx = 0
  contGC.gridy = 0
  cont.add(sudukuPanel, contGC)

  contGC.weightx = 0.0
  contGC.weighty = 0.0
  contGC.fill = GridBagConstraints.HORIZONTAL
  contGC.gridx = 1
  cont.add(controlPanel, contGC)

  setContentPane(cont)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setSize(1200,1000)
  setVisible(true)
}
