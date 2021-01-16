package a.gui

import a.StandardSolver

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

      val pds = StandardSolver.generateApplicablePropagateDeterminationInferences(ssg)

      for(pd <- pds)
        if(pd.preconditionsMet())
          pd.applyInference()

      val nss = StandardSolver.generateApplicableNakedSingleInferences(ssg)

      for(ns <- nss)
        if(ns.preconditionsMet())
          ns.applyInference()

      val css = StandardSolver.generateApplicableClothedSingleInferences(ssg)

      for(cs <- css)
        if(cs.preconditionsMet())
          cs.applyInference()

      val bps = StandardSolver.generateApplicableBoxPairIdentificationInferences(ssg)

      for(bp <- bps) {
        if(bp.preconditionsMet())
          bp.applyInference()
      }

      val lps = StandardSolver.generateApplicableLinePairIdentificationInferences(ssg)

      for(lp <- lps) {
        if(lp.preconditionsMet())
          lp.applyInference()
      }

      sudukuPanel.updateDisplay()
    }
  })

  val showPosButton = new JButton("Show")
  val hidePosButton = new JButton("Hide")

  showPosButton.addActionListener(new ActionListener {
    override def actionPerformed(e: ActionEvent): Unit = {
      sudukuPanel.showAllPossibilities()
    }
  })
  hidePosButton.addActionListener(new ActionListener {
    override def actionPerformed(e: ActionEvent): Unit = {
      sudukuPanel.hideAllPossibilities()
    }
  })

  controlPanel.add(startButton)
  controlPanel.add(showPosButton)
  controlPanel.add(hidePosButton)
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
  setResizable(false)
  setVisible(true)
}
