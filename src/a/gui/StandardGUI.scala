package a.gui

import java.awt.GridBagLayout
import javax.swing.{JFrame, JPanel}

class StandardGUI extends JFrame{

  val cont: JPanel = new JPanel(new GridBagLayout)

  val sudukuPanel: StandardSudkuPanel = new StandardSudkuPanel

  cont.add(sudukuPanel)

  setContentPane(cont)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setSize(1000,1000)
  setVisible(true)
}
