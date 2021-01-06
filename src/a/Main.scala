package a

import a.gui.StandardSudkuPanel
import a.i.{NakedSingle, PropogateDetermination}

import java.awt.GridBagLayout
import javax.swing.{JFrame, JPanel}

object Main extends App {

  val pan = new StandardSudkuPanel

  val frame = new JFrame
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  frame.setSize(1000,1000)

  val cont = new JPanel(new GridBagLayout)
  cont.add(pan)
  frame.setContentPane(cont)

  frame.setVisible(true)

}
