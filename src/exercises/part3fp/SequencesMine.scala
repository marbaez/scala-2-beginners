package exercises.part3fp

object SequencesMine extends App {

  val aSequence = Seq(4,2,3,1)
  println(aSequence)
  println(aSequence(2))
  println(aSequence.reverse)
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10 // includes 10
  val anotherRange: Seq[Int] = 1 until 10 // not includes 10
  aRange.foreach(println)

  // List
  val aList = List(1,2,3)
  val prependAndAppend = 42 +: aList :+ 84
  println(prependAndAppend)

  val fiveApples = List.fill(5)("apple")
  println(fiveApples)

  println(aList.mkString("--"))

  // Arrays
  val numbers = Array(1,2,3,4)



}
