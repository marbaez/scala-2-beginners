package exercises.part3fp

import scala.reflect.internal.Trees

object WhatsAFunction extends App {
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // Function types
  val string2int = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(string2int("3") + 4)

  // Exercises
  // 1. A function that takes 2 strigns and concatenate them
  val concatStrings = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concatStrings("hello", " world"))

  // 3.
  def multiplier = new Function[Int, (Int) => Int] {
    override def apply(v1: Int): Int => Int = x => x * v1
  }

  val doublerF = multiplier(2)
  println(doublerF(4))

}

trait MyFunction[A, B] {
  def apply(element: A): B
}
