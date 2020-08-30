import java.awt.event.{KeyEvent, KeyListener}

import javax.swing.JFrame

object Main extends App {

  println("Welcome to Sudku")
  // this is the easy one
  val breezy = "521822723327228429131933434236737839542844946648155262764566368271373174876577679881982583487188389"
  val harder = "211121225827628932833238439145646748858761162364568472284591694895796"
  var sg = new SimpleGrid(initial = breezy)

  println(sg.toString)
  sg.prettyPrint

  var f : JFrame = new JFrame()

  var s : SimpleGUIPanel = new SimpleGUIPanel(sg)

  f.addKeyListener(new KeyListener {
    override def keyTyped(e: KeyEvent): Unit = {

      val solver = new SudkuSolver(sg)
      val m : Option[String] = solver.naked_search(sg)
      println(m)
      if (m.isDefined){

        sg = sg.apply(m.get)

        s.setGrid(sg)
        f.repaint()
      }


    }

    override def keyPressed(e: KeyEvent): Unit = {}

    override def keyReleased(e: KeyEvent): Unit = {}
  })

  f.setContentPane(s)
  f.pack()
  f.setVisible(true)
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)


  //println(sg.valid)

  //var solver = new SudkuSolver(sg)

}
