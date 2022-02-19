package exercises.part3fp

import java.lang
import scala.annotation.tailrec

object HOFsCurriesMien extends App {

  //val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???
  //Higher Order Function (HOF)

  // function that applies a function n times over a value x
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  def plusOne: Int => Int = _ + 1
  println(nTimes(plusOne, 10, 1))

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // function with multiple parameter lists
  def curriedFormatter(c: String)(x: Double) : String = c.format(x)

  def standardFormat: (Double) => String = curriedFormatter("%4.2f")
  def preciseFormat: (Double) => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    (x: Int) => (y: Int) => f(x, y)

  def fromCurry(f:  (Int => Int => Int)): (Int, Int) => Int =
    (x: Int, y: Int) => f(x)(y)

  def compose(f: Int => Int, g: Int => Int) = (x: Int) => f(g(x))
  def andThen(f: Int => Int, g: Int => Int) = (x: Int) => g(f(x))
}
