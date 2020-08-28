object Main extends App {

  println("Welcome to Sudku")
  val initial = "521822723327228429131933434236737839542844946648155262764566368271373174876577679881982583487188389"
  var sg = new SimpleGrid(initial = initial)

  print(sg.toString)
  sg.prettyPrint()

  print(sg.valid)

}
